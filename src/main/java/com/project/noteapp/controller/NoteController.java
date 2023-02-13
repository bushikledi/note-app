package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.services.NoteServices;
import com.project.noteapp.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/{user_id}")
@AllArgsConstructor
public class NoteController {

    private NoteServices noteServices;
    private UserServices userServices;

    @PostMapping("/new-note")
    public String newNote(@RequestBody Note note, @PathVariable Integer user_id, Principal principal) {
        if (checkIfAuthorizedUser(user_id, principal)) {
            try {
                noteServices.newNote(user_id, note);
            } catch (Exception e) {
                return e.getMessage();
            }
            return "Note saved!";
        }
        return "Trying to access other User !!!";
    }

    @PostMapping("/edit-note/{note_id}")
    public String editNote(@RequestBody Note note, @PathVariable("note_id") Integer note_id,
                           @PathVariable Integer user_id, Principal principal) {
        if (checkIfAuthorizedUser(user_id, principal)) {
            try {
                noteServices.editNote(note_id, note);
            } catch (Exception e) {
                return e.getMessage();
            }
            return "note edited";
        }
        return "Trying to access other User !!!";
    }

    @GetMapping("/all-notes")
    public List<Note> getAllUserNotes(@PathVariable Integer user_id, Principal principal) {
        if (checkIfAuthorizedUser(user_id,principal))
        return noteServices.getAllUserNotes(user_id);
        else return null;
    }

    private boolean checkIfAuthorizedUser(Integer id, Principal principal) {
        return principal.getName().equals(userServices.getUserById(id).getUsername());
    }


}
