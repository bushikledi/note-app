package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;

import java.util.List;

public interface NoteServices {
    void newNote(Note note, User user);

    void editNote(Integer noteId, Note note, User user);

    void deleteNote(Integer noteId, User user);

    Note getNoteById(User user, Integer noteId);

    List<Note> getAllUserNotes(User user);

    List<Note> getNotesByName(User user, String name);

}
