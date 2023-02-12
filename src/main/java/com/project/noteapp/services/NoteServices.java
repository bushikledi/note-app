package com.project.noteapp.services;

import com.project.noteapp.model.Note;

public interface NoteServices {
    void newNote(Note note);

    void editNote(Note note);

    boolean deleteNote(Integer id);

    Note getNoteById(Integer id);
}
