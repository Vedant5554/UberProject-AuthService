package com.example.uberprojectauthservice.service;


import com.example.uberprojectauthservice.dtos.PassengerRequest;
import com.example.uberprojectauthservice.dtos.PassengerResponse;
import com.example.uberprojectauthservice.modles.Passenger;
import com.example.uberprojectauthservice.repository.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerResponse signupPassenger(PassengerRequest passengerRequest) {
        Passenger passenger = Passenger.builder()
                .email(passengerRequest.getEmail())
                .name(passengerRequest.getName())
                .password(bCryptPasswordEncoder.encode( passengerRequest.getPassword()))
                .phoneNumber(passengerRequest.getPhoneNumber())
                .build();

        Passenger newPassenger =passengerRepository.save(passenger);

        return PassengerResponse.from(newPassenger);
    }
}
