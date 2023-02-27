package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.services.NoteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServices noteServices;

    @PostMapping("/new")
    public ResponseEntity<Void> newNote(@RequestBody Note note, @AuthenticationPrincipal final User user) {
        noteServices.newNote(note, user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/edit/{note_id}")
    public ResponseEntity<Void> editNote(@RequestBody Note note, @PathVariable("note_id") Integer note_id,
                                         @AuthenticationPrincipal final User user) {
        if (noteServices.editNote(note_id, note, user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllUserNotes(@AuthenticationPrincipal final User user) {
        return ResponseEntity.status(OK).body(noteServices.getAllUserNotes(user));
    }

    @GetMapping("/note-name/{name}")
    public ResponseEntity<List<Note>> searchNotes(@PathVariable String name,
                                                  @AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(noteServices.getNotesByName(user, name));
    }

    @GetMapping("/note-id/{note_id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Integer note_id,
                                            @AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(noteServices.getNoteById(user, note_id));
    }

    @DeleteMapping("/delete/{note_id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer note_id,
                                           @AuthenticationPrincipal final User user) {
        if (
                noteServices.deleteNote(note_id, user)) {
            return new ResponseEntity<>(OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
