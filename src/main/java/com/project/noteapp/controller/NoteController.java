package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.services.NoteServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{user_id}/")
@AllArgsConstructor
public class NoteController {

    private NoteServices noteServices;

    @PostMapping("new")
    public void newNote(@RequestBody Note note){
        noteServices.newNote(note);
    }
}
