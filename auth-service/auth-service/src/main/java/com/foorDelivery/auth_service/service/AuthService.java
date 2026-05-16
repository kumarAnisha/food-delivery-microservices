package com.foorDelivery.auth_service.service;

import com.foorDelivery.auth_service.DTO.JwtResponse;
import com.foorDelivery.auth_service.DTO.LoginRequest;
import com.foorDelivery.auth_service.DTO.SignUpRequest;
import com.foorDelivery.auth_service.entity.User;
import com.foorDelivery.auth_service.repositary.UserRepositary;
import com.foorDelivery.auth_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private JwtUtil jwtUtil;
    private UserRepositary userRepositary;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public AuthService(JwtUtil jwtUtil, UserRepositary userRepositary){
        this.jwtUtil=jwtUtil;
        this.userRepositary=userRepositary;
    }
    public void registerUser(SignUpRequest signUpRequest){
        if(userRepositary.findUserByUsername(signUpRequest.getUsername()).isPresent()){
            throw new RuntimeException("User already exists");
        }
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setEmail(signUpRequest.getEmail());
           userRepositary.save(user);

    }
    public JwtResponse authenticateUser(LoginRequest loginRequest){
        Optional<User> userOpt = userRepositary.findUserByUsername(loginRequest.getUsername());
                if(userOpt.isPresent()){
                    User user = userOpt.get();
                    if(passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
                        String token = jwtUtil.generateToken(user.getUsername());
                        return new JwtResponse(token);
                    } else {
                        throw new RuntimeException("Invalid credentials");
                    }
                    }


                else{
                    throw new RuntimeException("Invalid crenditials");
                }
    }
}
