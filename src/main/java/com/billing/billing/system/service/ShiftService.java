package com.billing.billing.system.service;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.mapper.ShiftReportMapper;
import com.billing.billing.system.model.*;
import com.billing.billing.system.payload.dto.ShiftReportDTO;
import com.billing.billing.system.repository.OrderRepository;
import com.billing.billing.system.repository.RefundRepository;
import com.billing.billing.system.repository.ShiftRepository;
import com.billing.billing.system.service.impl.IShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShiftService implements IShiftService {
    private final ShiftRepository shiftRepository;
    private  final UserService userService;
    private  final RefundRepository refundRepository;
    private  final OrderRepository orderRepository;

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

        return null;
    }

    @Override
    public ShiftReportDTO getShiftReportById(Long id) {
        return null;
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) {
        return null;
    }
}
