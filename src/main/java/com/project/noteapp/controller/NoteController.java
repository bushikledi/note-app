package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.services.NoteServices;
import com.project.noteapp.services.NoteServicesImplementation;
import com.project.noteapp.services.UserServices;
import com.project.noteapp.services.UserServicesImplementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{user_id}")
@AllArgsConstructor
public class NoteController {

    private NoteServices noteServices;
    @PostMapping("/new-note")
    public String newNote(@RequestBody Note note, @PathVariable Integer user_id){
        try {
            noteServices.newNote(user_id,note);
        }catch (Exception e)
        {
            return e.getMessage();
        }
        return "note saved";
    }

    @PostMapping("/edit-note/{note_id}")
    public String editNote(@RequestBody Note note, @PathVariable Integer note_id){
        try {
            noteServices.editNote(note_id,note);
        }catch (Exception e)
        {
            return e.getMessage();
        }
        return "note edited";
    }
}
