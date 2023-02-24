package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.services.NoteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServices noteServices;

    @PostMapping("/new")
    public ResponseEntity<Void> newNote(@RequestBody Note note, Authentication authentication) {
        noteServices.newNote(((User) authentication.getPrincipal()).getUserId(), note);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/edit/{note_id}")
    public ResponseEntity<Void> editNote(@RequestBody Note note, @PathVariable("note_id") Integer note_id,
                                         Authentication authentication) {
        if (noteServices.editNote(note_id, note,
                ((User) authentication.getPrincipal()).getUserId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllUserNotes(Authentication authentication) {
        return ResponseEntity.status(OK).body(noteServices.getAllUserNotes(((User) authentication.getPrincipal()).getUserId()));
    }

    @GetMapping("/note/{name}")
    public ResponseEntity<List<Note>> searchNotes(@PathVariable String name, Authentication authentication) {
        return ResponseEntity.ok(noteServices.getNotesByName(((User) authentication.getPrincipal()).getUserId(), name));
    }

    @GetMapping("/note/{note_id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Integer note_id, Authentication authentication) {
        return ResponseEntity.ok(noteServices.getNoteById(((User) authentication.getPrincipal()).getUserId(), note_id));
    }

    @DeleteMapping("/delete/{note_id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer note_id, Authentication authentication) {
        noteServices.deleteNote(note_id,
                ((User) authentication.getPrincipal()).getUserId());
        return new ResponseEntity<>(OK);
    }


}
