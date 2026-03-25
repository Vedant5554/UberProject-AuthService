package com.example.uberprojectauthservice.dtos;


import com.example.uberprojectauthservice.modles.Passenger;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponse {
    private Long id;
    private String name;
    private String email;
    private String password; //encrypted
    private String phoneNumber;

    private LocalDate createdAt;

    public static PassengerResponse from(Passenger p) {
        PassengerResponse result = PassengerResponse.builder()
                .id(p.getId())
                .createdAt(LocalDate.from(p.getCreatedAt()))
                .email(p.getEmail())
                .password(p.getPassword())
                .phoneNumber(p.getPhoneNumber())
                .name(p.getName())
                .build();
        return result;
    }
}
