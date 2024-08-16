package com.patika.bloghubuserservice.controller;

import com.patika.bloghubuserservice.dto.request.UserLoginRequest;
import com.patika.bloghubuserservice.dto.request.UserSaveRequest;
import com.patika.bloghubuserservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserSaveRequest request) {
        authService.register(request);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}