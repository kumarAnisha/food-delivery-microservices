package com.foorDelivery.auth_service.controller;

import com.foorDelivery.auth_service.DTO.JwtResponse;
import com.foorDelivery.auth_service.DTO.LoginRequest;
import com.foorDelivery.auth_service.DTO.SignUpRequest;
import com.foorDelivery.auth_service.service.AuthService;
import com.foorDelivery.auth_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthService authService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpRequest signUpRequest){
        authService.registerUser(signUpRequest);
        return "User register Successfully";
    }
    @PostMapping("/login")
    public JwtResponse loginUser(@RequestBody LoginRequest loginRequest){
        //loginRequest.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        return authService.authenticateUser(loginRequest);
    }
    @GetMapping("/d")
    public String dummy(){
        return "dummy";
    }
}
