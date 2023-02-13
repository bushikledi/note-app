package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @GetMapping("/{user_id}")
    public User getUser(@PathVariable Integer user_id,Principal principal) {
        if (checkIfAuthorizedUser(user_id,principal)) {
            return userServices.getUserById(user_id);
        } else return null;
    }

    private boolean checkIfAuthorizedUser(Integer id,Principal principal) {
        return principal.getName().equals(userServices.getUserById(id).getUsername());
    }
}
