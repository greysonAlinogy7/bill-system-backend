package com.billing.billing.system.payload.dto;

import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BranchDTO {
    private Long id;
    private  String name;
    private  String address;
    private  String phone;
    private  String email;
    private List<String> workingDays;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private StoreDTO store;
    private Long storeId;
    private UserDTO manager;
}
