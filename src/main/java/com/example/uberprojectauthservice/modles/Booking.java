package com.example.uberprojectauthservice.modles;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel{



    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "start_time")
    private LocalDateTime startTime;


    @Column(name = "end_time")
    private LocalDateTime endTime;

    private Long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Passenger passenger;

}
