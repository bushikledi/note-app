package com.project.noteapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Table(name = "users")
@Getter @Setter @RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String photo;
    @OneToMany
    private List<Note> notes;

}
