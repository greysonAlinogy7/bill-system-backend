package com.billing.billing.system.mapper;

import com.billing.billing.system.model.OrderItem;
import com.billing.billing.system.payload.dto.OrderItemDTO;

public class OrderItemMapper {
    public  static OrderItemDTO toDTO(OrderItem orderItem){
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .product(ProductMapper.toDTO(orderItem.getProduct()))
                .build();
    }
}
