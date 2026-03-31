package com.billing.billing.system.payload.dto;

import com.billing.billing.system.model.Branch;
import com.billing.billing.system.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {
    private Long id;


    private BranchDTO branch;
    private Long branchId;
    private Long productId;


    private ProductDTO product;


    private Integer quantity;


    private LocalDateTime lastUpdate;
}
