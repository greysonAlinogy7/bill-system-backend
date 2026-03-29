package com.billing.billing.system.service.impl;

import com.billing.billing.system.exception.UserException;
import com.billing.billing.system.payload.dto.UserDTO;
import com.billing.billing.system.payload.request.LoginRequest;
import com.billing.billing.system.payload.response.AuthResponse;

public interface IAuthService {
    AuthResponse Register(UserDTO userDTO) throws Exception, UserException;
    AuthResponse login(LoginRequest loginRequest) throws UserException;
}
