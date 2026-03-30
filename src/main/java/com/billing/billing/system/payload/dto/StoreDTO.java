package com.billing.billing.system.payload.dto;

import com.billing.billing.system.domain.StoreStatus;
import com.billing.billing.system.model.StoreContact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {
    private Long id;
    private  String brand;
    private UserDTO storeAdmin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private  String description;
    private String storeType;
    private StoreStatus status;
    private StoreContact contact ;
}
