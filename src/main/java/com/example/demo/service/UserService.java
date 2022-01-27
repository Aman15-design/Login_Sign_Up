package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.web.dto.UserRegistrationDto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService{

    // this is the method that will just save User Registered data
    User save(UserRegistrationDto registrationDto);
}
