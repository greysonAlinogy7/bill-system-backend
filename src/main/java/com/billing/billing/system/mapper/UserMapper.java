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

    public static User toEntity(UserDTO userDTO){
        User createdUser = new User();
        createdUser.setEmail(userDTO.getEmail());
        createdUser.setFullName(userDTO.getFullName());
        createdUser.setRole(userDTO.getRole());
        createdUser.setCreatedAt(userDTO.getCreatedAt());
        createdUser.setUpdatedAt(userDTO.getUpdatedAt());
        createdUser.setLastLogin(userDTO.getLastLogin());
        createdUser.setPhone(userDTO.getPhone());
        createdUser.setPassword(userDTO.getPassword());
        return createdUser;

    }

}
