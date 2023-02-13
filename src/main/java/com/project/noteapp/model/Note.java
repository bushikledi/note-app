package com.project.noteapp.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "notes")
@Getter
@Setter
@RequiredArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Integer noteId;
    private String noteName;
    private String note;
    private LocalDate createdDate;
    private LocalDate editedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
