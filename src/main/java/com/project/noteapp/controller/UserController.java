package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/user/{user_id}")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @GetMapping
    public ResponseEntity<User> getUser(@PathVariable Integer user_id) {
        return new ResponseEntity<>(userServices.getUserById(user_id), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(@PathVariable Integer user_id, @RequestBody User user) {
        userServices.updateUser(user_id, user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer user_id) {
        userServices.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
