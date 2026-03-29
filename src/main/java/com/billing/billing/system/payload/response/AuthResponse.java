package com.billing.billing.system.payload.response;

import com.billing.billing.system.payload.dto.UserDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDTO user;
}
