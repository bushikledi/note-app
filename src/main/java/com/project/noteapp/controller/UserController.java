package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

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
                                           @RequestBody User update) {
        userServices.updateUser(user, update);
        return new ResponseEntity<>(OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal final User user) {
        userServices.deleteUser(user);
        return new ResponseEntity<>(OK);
    }
}
