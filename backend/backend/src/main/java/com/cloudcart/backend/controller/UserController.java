package com.cloudcart.backend.controller;

import com.cloudcart.backend.entity.User;
import com.cloudcart.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // Get Profile
    @GetMapping("/{email}")
    public User getProfile(@PathVariable String email) {

        return userService.getProfile(email);

    }

    // Update Profile
    @PutMapping("/update")
    public User updateProfile(@RequestBody User user) {

        return userService.updateProfile(user);

    }

}