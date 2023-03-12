package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;

import java.util.List;

public interface NoteServices {
    boolean newNote(Note note, User user);

    boolean editNote(Integer noteId, Note note, User user);

    boolean deleteNote(Integer noteId, User user);

    Note getNoteById(User user, Integer noteId);

    List<Note> getAllUserNotes(User user);

    List<Note> getNotesByName(User user, String name);

}
