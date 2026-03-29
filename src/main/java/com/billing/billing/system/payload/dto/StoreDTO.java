package com.billing.billing.system.payload.dto;

import com.billing.billing.system.domain.StoreStatus;
import com.billing.billing.system.model.StoreContact;
import com.billing.billing.system.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
