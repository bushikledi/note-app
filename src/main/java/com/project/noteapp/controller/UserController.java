package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @PostMapping("/new")
    public void newUser(@RequestBody User user) {
        userServices.newUser(user);
    }

    @GetMapping("/user-data")
    public User getUser() {
        return userServices.getUserById(1);
    }
}
