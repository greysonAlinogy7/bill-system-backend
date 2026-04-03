package com.billing.billing.system.mapper;

import com.billing.billing.system.model.Order;
import com.billing.billing.system.payload.dto.OrderDTO;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO orderDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getType())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream().map(OrderItemMapper::toDTO).collect(Collectors.toList()))
                .build();

    }
}
