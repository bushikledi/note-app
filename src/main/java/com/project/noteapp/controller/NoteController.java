package com.project.noteapp.controller;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.services.NoteServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("authenticated")
    public ResponseEntity<Void> newNote(@RequestBody Note note,
                                        @AuthenticationPrincipal final User user) {
        if (noteServices.newNote(note, user)) {
            return new ResponseEntity<>(CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("authenticated")
    public ResponseEntity<Void> editNote(@RequestBody Note note,
                                         @PathVariable("id") Integer id,
                                         @AuthenticationPrincipal final User user) {
        if (noteServices.editNote(id, note, user)) {
            return new ResponseEntity<>(OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("authenticated")
    public ResponseEntity<List<Note>> getAllUserNotes(@AuthenticationPrincipal final User user) {
        return ResponseEntity.status(OK).body(noteServices.getAllUserNotes(user));
    }

    @GetMapping("/search/name/{name}")
    @PreAuthorize("authenticated")
    public ResponseEntity<List<Note>> searchNotes(@PathVariable String name,
                                                  @AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(noteServices.getNotesByName(user, name));
    }

    @GetMapping("/search/id/{id}")
    @PreAuthorize("authenticated")
    public ResponseEntity<Note> getNoteById(@PathVariable Integer id,
                                            @AuthenticationPrincipal final User user) {
        return ResponseEntity.ok(noteServices.getNoteById(user, id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("authenticated")
    public ResponseEntity<Void> deleteNote(@PathVariable Integer id,
                                           @AuthenticationPrincipal final User user) {
        if (noteServices.deleteNote(id, user)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
