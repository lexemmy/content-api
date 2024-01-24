package com.example.lab1.service.impl;

import com.example.lab1.dto.request.LoginRequest;
import com.example.lab1.dto.request.RefreshTokenRequest;
import com.example.lab1.dto.response.LoginResponse;
import com.example.lab1.model.User;
import com.example.lab1.service.AuthService;
import com.example.lab1.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User principal = (User) authentication.getPrincipal();
        String token = jwtUtil.issueToken(principal.getEmail(), principal.getRoles().toString());
        return new LoginResponse(token);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return null;
    }

}
