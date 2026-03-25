package com.example.uberprojectauthservice.controller;


import com.example.uberprojectauthservice.dtos.PassengerRequest;
import com.example.uberprojectauthservice.dtos.PassengerResponse;
import com.example.uberprojectauthservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponse> signUp(@RequestBody PassengerRequest passengerRequest) {
        PassengerResponse response = authService.signupPassenger(passengerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signIn(){

        return  new ResponseEntity<>(10,HttpStatus.CREATED);
    }

}
