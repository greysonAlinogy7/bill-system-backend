package com.billing.billing.system.controller;


import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.mapper.UserMapper;
import com.billing.billing.system.model.User;
import com.billing.billing.system.payload.dto.UserDTO;
import com.billing.billing.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private  final UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws UserException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
