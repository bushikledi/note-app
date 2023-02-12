package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{user_id}/")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @PostMapping("new/user")
    public void newUser(@RequestBody User user) {
        userServices.newUser(user);
    }
}
