package com.example.lab1.service;

import com.example.lab1.dto.request.LoginRequest;
import com.example.lab1.dto.request.RefreshTokenRequest;
import com.example.lab1.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
