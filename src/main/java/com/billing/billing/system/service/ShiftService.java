package com.billing.billing.system.service;

import com.billing.billing.system.domain.PaymentType;
import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.mapper.ShiftReportMapper;
import com.billing.billing.system.model.*;
import com.billing.billing.system.payload.dto.ShiftReportDTO;
import com.billing.billing.system.repository.OrderRepository;
import com.billing.billing.system.repository.RefundRepository;
import com.billing.billing.system.repository.ShiftRepository;
import com.billing.billing.system.repository.UserRepository;
import com.billing.billing.system.service.impl.IShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftService implements IShiftService {
    private final ShiftRepository shiftRepository;
    private  final UserService userService;
    private  final RefundRepository refundRepository;
    private  final OrderRepository orderRepository;
    private  final UserRepository userRepository;


    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {
        User currentUser = userService.getCurrentUser();
        shiftStart = LocalDateTime.now();
        LocalDateTime startOfDays = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay=shiftStart.withHour(23).withMinute(59).withSecond(59);

        Optional<ShiftReport> existing = shiftRepository.findByCashierAndShiftStartBetween(currentUser, startOfDays, endOfDay);
        if (existing.isPresent()){
            throw new Exception("shift already started today");
        }
        Branch branch = currentUser.getBranch();
        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(branch)
                .build();
        ShiftReport savedShiftReport = shiftRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedShiftReport);
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
        User currentUser = userService.getCurrentUser();
        ShiftReport shiftReport = shiftRepository
                .findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(currentUser).orElseThrow(() -> new Exception("shift not found"));
        shiftReport.setShiftEnd(shiftEnd);
        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
                currentUser.getId(),
                shiftReport.getShiftStart(), shiftReport.getShiftEnd()
        );
        List<Order> orders =orderRepository.findByCashierAndCreatedAtBetween(currentUser, shiftReport.getShiftStart(), shiftReport.getShiftEnd());
        double totalRefunds = refunds.stream().mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();
        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        int totalOrders=orders.size();
        double netSales=totalSales-totalRefunds;
        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSales(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport = shiftRepository.save(shiftReport);

        return ShiftReportMapper.toDTO(savedReport);
    }

    private  List<PaymentSummary>  getPaymentSummaries(List<Order> orders, double totalSales) {
        Map<PaymentType, List<Order>> grouped = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getType() != null ? order.getType() : PaymentType.CASH));

        List<PaymentSummary> summaries = new ArrayList<>();

        for (Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()){
            double amount = entry.getValue().stream().mapToDouble(Order::getTotalAmount).sum();

            int transaction = entry.getValue().size();
            double percentage =(amount/totalSales)*100;

            PaymentSummary ps = new PaymentSummary();
            ps.setPaymentType(entry.getKey());
            ps.setTotalAmount(amount);
            ps.setTransactionCount(transaction);
            ps.setPercentage(percentage);
            summaries.add(ps);


        }
        return summaries;

    }

    private List<Product> getTopSellingProducts(List<Order> orders) {
        Map<Product, Integer> productSale = new HashMap<>();

        for (Order order:orders){
            for (OrderItem item : order.getItems()){
                Product product = item.getProduct();
                productSale.put(product, productSale.getOrDefault(product, 0)+item.getQuantity());
            }
        }
        return productSale.entrySet()
                .stream()
                .sorted((a,b)-> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream().sorted(Comparator.comparing(Order::getCreatedAt).reversed()).limit(5).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getShiftReportById(Long id) throws Exception {
        return shiftRepository.findById(id).map(ShiftReportMapper::toDTO).orElseThrow(() -> new Exception("shift report not found "));
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        List<ShiftReport> reports = shiftRepository.findAll();
        return reports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId) {
        List<ShiftReport> reports = shiftRepository.findByCashierId(cashierId);
        return reports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByBranchId(Long branchId) {
        List<ShiftReport> reports = shiftRepository.findByBranchId(branchId);
        return reports.stream().map(ShiftReportMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception {
        User user = userService.getCurrentUser();

        ShiftReport shiftReport = shiftRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(user).orElseThrow(() -> new Exception("shift not found"));

        LocalDateTime now = LocalDateTime.now();

        List<Order> orders = orderRepository.findByCashierAndCreatedAtBetween(user, shiftReport.getShiftStart(), now);

        List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
                user.getId(),
                shiftReport.getShiftStart(), shiftReport.getShiftEnd()
        );

        double totalRefunds = refunds.stream().mapToDouble(refund -> refund.getAmount() != null ? refund.getAmount() : 0.0).sum();

        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        int totalOrders=orders.size();
        double netSales=totalSales-totalRefunds;
        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setNetSales(netSales);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport = shiftRepository.save(shiftReport);


        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        User cashier = userRepository.findById(cashierId).orElseThrow(() -> new Exception("cashier not found"));
        LocalDateTime start = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = date.withHour(23).withMinute(59).withSecond(59);
        ShiftReport shiftReport = shiftRepository.findByCashierAndShiftStartBetween(cashier, start, end).orElseThrow(() -> new Exception("shift report not found for cashier"));
        return ShiftReportMapper.toDTO(shiftReport);
    }
}
