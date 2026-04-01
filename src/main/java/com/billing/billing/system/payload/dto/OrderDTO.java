package com.billing.billing.system.payload.dto;

import com.billing.billing.system.domain.OrderStatus;
import com.billing.billing.system.domain.PaymentType;
import com.billing.billing.system.model.Customer;
import com.billing.billing.system.model.OrderItem;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Double totalAmount;
    private PaymentType paymentType;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private Long branchId;
    private Long customerId;
    private BranchDTO branch;
    private UserDTO cashier;
    private Customer customer;
    private List<OrderItemDTO> items;
}
