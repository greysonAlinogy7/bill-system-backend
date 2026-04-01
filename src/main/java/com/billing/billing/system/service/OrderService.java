package com.billing.billing.system.service;

import com.billing.billing.system.domain.OrderStatus;
import com.billing.billing.system.domain.PaymentType;
import com.billing.billing.system.mapper.OrderMapper;
import com.billing.billing.system.model.*;
import com.billing.billing.system.payload.dto.OrderDTO;
import com.billing.billing.system.repository.OrderItemRepository;
import com.billing.billing.system.repository.OrderRepository;
import com.billing.billing.system.repository.ProductRepository;
import com.billing.billing.system.service.impl.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private  final OrderRepository orderRepository;
    private  final UserService userService;
    private  final ProductRepository productRepository;
    private  final OrderItemRepository orderItemRepository;



    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws Exception {
        User cashier = userService.getCurrentUser();
        Branch branch = cashier.getBranch();
        if (branch==null){
            throw  new Exception("cashier's branch not found");
        }
        Order order =Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDTO.getCustomer())
                .status(OrderStatus.PENDING)
                .type(orderDTO.getPaymentType())
                .build();

        List<OrderItem> orderItems = orderDTO.getItems().stream().map(itemsDto ->{
            Product product = productRepository.findById(itemsDto.getProductId()).orElseThrow(() -> new EntityNotFoundException("product not found"));
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemsDto.getQuantity())
                    .price(product.getSellingPrice() * itemsDto.getQuantity())
                    .order(order)
                    .build();
            return orderItem;
        }
        ).toList();
        double total = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.orderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) throws Exception {
        return OrderMapper.orderDTO(orderRepository.findById(id).orElseThrow(() -> new Exception("order not found")));
    }

    @Override
    public List<OrderDTO> getOrdersByBranch(Long branchId,
                                            Long customerId,
                                            Long cashierId,
                                            PaymentType paymentType,
                                            OrderStatus status) {
        return orderRepository.findByBranchId(branchId).stream().filter(order -> customerId==null || (order.getCustomer()!=null && order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId==null || order.getCashier()!=null && order.getCashier().getId().equals(cashierId))
                .filter(order -> paymentType==null || order.getType()==paymentType).map(OrderMapper::orderDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByCashier(Long cashierId) {
        return orderRepository.findByCashierId(cashierId).stream().map(OrderMapper::orderDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
        Order order = orderRepository.findById(id).orElseThrow(() -> new Exception("order not found with id"));
        orderRepository.delete(order);

    }

    @Override
    public List<OrderDTO> getTodayOrdersByBranch(Long branchId) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();
        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start,end).stream().map(OrderMapper::orderDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderMapper::orderDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId) {
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::orderDTO).collect(Collectors.toList());
    }
}
