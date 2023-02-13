package com.project.noteapp.services;

import com.project.noteapp.model.Note;

public interface NoteServices {
    void newNote(Integer id, Note note);

    void editNote(Integer note_id,Note note);

    boolean deleteNote(Integer id);

    Note getNoteById(Integer id);
}
