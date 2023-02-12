package com.project.noteapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GlobalController {
    @GetMapping("/login")
    public String hello() {
        return "loggedIn";
    }
}
