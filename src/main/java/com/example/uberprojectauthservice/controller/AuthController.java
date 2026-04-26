package com.example.uberprojectauthservice.controller;


import com.example.uberprojectauthservice.dtos.AuthRequestDto;
import com.example.uberprojectauthservice.dtos.PassengerRequest;
import com.example.uberprojectauthservice.dtos.PassengerResponse;
import com.example.uberprojectauthservice.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerResponse> signUp(@RequestBody PassengerRequest passengerRequest) {
        PassengerResponse response = authService.signupPassenger(passengerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );
        if(authentication.isAuthenticated()) {
            return new ResponseEntity<>("Successful auth",HttpStatus.OK);
        }else{
            throw  new UsernameNotFoundException("User not found");
        }

    }

}
