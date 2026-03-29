package com.billing.billing.system.mapper;

import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.UserDTO;

public class UserMapper {
    public static UserDTO toDTO(User savedUser){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setFullName(savedUser.getFullName());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setRole(savedUser.getRole());
        userDTO.setCreatedAt(savedUser.getCreatedAt());
        userDTO.setUpdatedAt(savedUser.getUpdatedAt());
        userDTO.setLastLogin(savedUser.getLastLogin());
        userDTO.setPhone(savedUser.getPhone());
        return userDTO;

    }
}
