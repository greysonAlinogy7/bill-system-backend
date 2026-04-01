package com.billing.billing.system.payload.dto;

import com.billing.billing.system.domain.OrderStatus;
import com.billing.billing.system.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {
    private Long id;

    private Integer quantity;


    private Double price;

    private ProductDTO product;
    private Long productId;

    private Long orderId;
}
