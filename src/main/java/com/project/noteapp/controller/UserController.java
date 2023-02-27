package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServicesImplementation userServices;

    @GetMapping("/home")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal final User user,
                                           @RequestBody User updateUser) {
        userServices.updateUser(user, updateUser);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal final User user) {
        userServices.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
