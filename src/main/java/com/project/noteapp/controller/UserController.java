package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServicesImplementation userServices;

    @GetMapping("/home")
    public ResponseEntity<User> getUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(
                User.builder()
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .username(user.getUsername())
                        .photo(user.getPhoto())
                        .role(user.getRole())
                        .build()
        );
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateUser(Authentication authentication, @RequestBody User user) {
        userServices.updateUser(((User) authentication.getPrincipal()).getUserId(), user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userServices.deleteUser(((User) authentication.getPrincipal()).getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
