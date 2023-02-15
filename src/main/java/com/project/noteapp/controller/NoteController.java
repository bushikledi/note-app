package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.services.NoteServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/notes")
@AllArgsConstructor
public class NoteController {

    private NoteServices noteServices;

    @PostMapping("/new-note/{user_id}")
    public ResponseEntity<Void> newNote(@RequestBody Note note, @PathVariable Integer user_id) {
        noteServices.newNote(user_id, note);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/edit-note/{note_id}")
    public ResponseEntity<Void> editNote(@RequestBody Note note, @PathVariable("note_id") Integer note_id) {
        noteServices.editNote(note_id, note);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-notes/{user_id}")
    public ResponseEntity<List<Note>> getAllUserNotes(@PathVariable Integer user_id) {
        return ResponseEntity.status(OK).body(noteServices.getAllUserNotes(user_id));
    }

    @DeleteMapping("/delete-note/{note_id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer note_id) {
        noteServices.deleteNote(note_id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/search-notes/{user_id}/{name}")
    public ResponseEntity<List<Note>> getNotesByName(@PathVariable String name, @PathVariable Integer user_id) {
        return ResponseEntity.status(OK).body(noteServices.getNotesByName(user_id, name));
    }


}
