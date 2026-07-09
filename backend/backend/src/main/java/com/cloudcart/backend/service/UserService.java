package com.cloudcart.backend.service;

import com.cloudcart.backend.entity.User;
import com.cloudcart.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get User
    public User getProfile(String email) {

        return userRepository
                .findByEmail(email)
                .orElseThrow();
    }

    // Update User
    public User updateProfile(User user) {

        return userRepository.save(user);
    }

}