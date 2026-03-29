package com.billing.billing.system.payload.dto;

import com.billing.billing.system.domain.UserRole;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class UserDTO {
    private Long id;
    private  String fullName;
    private  String email;
    private  String phone;
    private  String password;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

}
