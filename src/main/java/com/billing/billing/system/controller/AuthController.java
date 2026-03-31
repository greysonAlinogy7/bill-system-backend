package com.billing.billing.system.controller;


import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.payload.dto.UserDTO;
import com.billing.billing.system.payload.request.LoginRequest;
import com.billing.billing.system.payload.response.AuthResponse;
import com.billing.billing.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private  final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDTO userDTO) throws UserException, Exception {
        return ResponseEntity.ok(authService.Register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signInHandler(@RequestBody LoginRequest loginRequest) throws UserException, Exception {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
