package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @GetMapping("/home")
    public ResponseEntity<User> getUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("Test!");
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
