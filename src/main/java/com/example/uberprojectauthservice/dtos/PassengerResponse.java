package com.example.uberprojectauthservice.dtos;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponse {

    private String name;
    private String email;
    private String password; //encrypted
    private String phoneNumber;

    private LocalDate createdAt;
}
