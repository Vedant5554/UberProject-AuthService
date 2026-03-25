package com.example.uberprojectauthservice.controller;


import com.example.uberprojectauthservice.dtos.PassengerRequest;
import com.example.uberprojectauthservice.dtos.PassengerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponse> signUp(@RequestBody PassengerRequest passengerRequest) {
        return null;
    }

}
