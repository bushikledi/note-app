package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;

import java.util.List;

public interface NoteServices {
    void newNote(Note note, User user);

    boolean editNote(Integer note_id, Note note, User user);

    boolean deleteNote(Integer noteId, User user);

    Note getNoteById(User user, Integer noteId);

    List<Note> getAllUserNotes(User user);

    List<Note> getNotesByName(User user, String name);

}
