package com.example.uberprojectauthservice.service;

import com.example.uberprojectauthservice.helper.AuthPassengerDetails;
import com.example.uberprojectauthservice.modles.Passenger;
import com.example.uberprojectauthservice.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * this class is responsible for loading the user in the form of UserDetails Object for auth
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private  PassengerRepository passengerRepository;

    public UserDetailsServiceImpl(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
// in our case the unique identification entity is email not username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(email);
        if(passenger.isPresent()) {
            return new AuthPassengerDetails(passenger.get());
        } else {
            throw new UsernameNotFoundException("can not find the passenger by the given Email" + email);
        }
    }
}
