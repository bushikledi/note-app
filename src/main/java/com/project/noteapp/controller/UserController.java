package com.project.noteapp.controller;

import com.project.noteapp.model.User;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/{user_id}")
@AllArgsConstructor
public class UserController {
    private UserServicesImplementation userServices;

    @GetMapping("/")
    public User getUser(@PathVariable Integer user_id, Principal principal) {
        if (checkIfAuthorizedUser(user_id, principal)) {
            return userServices.getUserById(user_id);
        } else return null;
    }

    @PostMapping("/update")
    public String updateUser(@PathVariable Integer user_id, @RequestBody User user, Principal principal) {
        if (checkIfAuthorizedUser(user_id, principal)) {
            userServices.updateUser(user_id, user);
            return "User updated!";
        } else return "Error!";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@PathVariable Integer user_id, Principal principal) {
        if (checkIfAuthorizedUser(user_id, principal)) {
            userServices.deleteUser(user_id);
            return "User deleted!";
        } else return "Error!!";
    }

    private boolean checkIfAuthorizedUser(Integer id, Principal principal) {
        return principal.getName().equals(userServices.getUserById(id).getUsername());
    }
}
