package com.example.lab1.controller;

import com.example.lab1.dto.request.LoginRequest;
import com.example.lab1.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping
    public void allAdmin() {
        System.out.println("Print all admins");
    }
}
