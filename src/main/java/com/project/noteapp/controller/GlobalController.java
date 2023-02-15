package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GlobalController {

    private UserServicesImplementation userServices;

    @GetMapping("/login")
    public String hello() {
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<Void> newUser(@RequestBody User user) {
        userServices.newUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
