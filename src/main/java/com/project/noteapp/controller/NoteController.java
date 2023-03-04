package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.services.NoteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServices noteServices;

    @PostMapping("/new")
    public ResponseEntity<Void> newNote(@RequestBody Note note,
                                        @AuthenticationPrincipal final User user) {
        noteServices.newNote(note, user);
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Void> editNote(@RequestBody Note note,
                                         @PathVariable("id") Integer id,
                                         @AuthenticationPrincipal final User user) {
        noteServices.editNote(id, note, user);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllUserNotes(@AuthenticationPrincipal final User user) {
        return ResponseEntity.status(OK).body(noteServices.getAllUserNotes(user));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Note>> searchNotes(@PathVariable String name,
                                                  @AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(noteServices.getNotesByName(user, name));
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Integer id,
                                            @AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(noteServices.getNoteById(user, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id,
                                           @AuthenticationPrincipal final User user) {
        noteServices.deleteNote(id, user);
        return ResponseEntity.noContent().build();
    }


}
