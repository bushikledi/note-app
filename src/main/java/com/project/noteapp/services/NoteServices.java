package com.project.noteapp.services;

import com.project.noteapp.model.Note;

import java.util.List;

public interface NoteServices {
    void newNote(Integer id, Note note);

    void editNote(Integer note_id,Note note);

    boolean deleteNote(Integer id);

    Note getNoteById(Integer id);

    List<Note> getAllUserNotes(Integer id);

    List<Note> getNotesByName(Integer user_id, String name);
}
