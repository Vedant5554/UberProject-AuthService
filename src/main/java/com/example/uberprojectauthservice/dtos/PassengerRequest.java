package com.example.uberprojectauthservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerRequest {
    private String id;

    private String email;
    private String password;
    private String name;
    private String phoneNumber;

}
