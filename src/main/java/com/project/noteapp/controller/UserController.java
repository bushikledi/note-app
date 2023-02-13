package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user/{user_id}")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @GetMapping("/info")
    public User getUser(@PathVariable Integer user_id, Principal principal) {
        if (principal.getName().equals(userServices.getUserById(user_id).getUsername())) {
            return userServices.getUserById(user_id);
        }
        else return null;
    }
}
