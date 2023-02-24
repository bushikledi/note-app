package com.project.noteapp.services;

import com.project.noteapp.model.Note;

import java.util.List;

public interface NoteServices {
    void newNote(Integer id, Note note);

    boolean editNote(Integer note_id, Note note, Integer userId);

    boolean deleteNote(Integer id, Integer userId);

    Note getNoteById(Integer id, Integer noteId);

    List<Note> getAllUserNotes(Integer id);

    List<Note> getNotesByName(Integer user_id, String name);

}
