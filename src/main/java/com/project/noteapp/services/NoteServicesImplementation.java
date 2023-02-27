package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServicesImplementation implements NoteServices {

    private final NoteRepository noteRepository;

    @Override
    @Transactional
    public void newNote(Note note, User user) {
        note.setUserId(user.getUserId());
        note.setCreatedDate(LocalDate.now());
        note.setEditedDate(LocalDate.now());
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public boolean editNote(Integer noteId, Note modNote, User user) {
        Note note = user.getNotes().get(noteId);
        if (note != null) {
            note.setNote(modNote.getNote());
            note.setNoteName(modNote.getNoteName());
            note.setEditedDate(LocalDate.now());
            noteRepository.save(note);
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public boolean deleteNote(Integer noteId, User user) {
        Note note = user.getNotes().get(noteId);
        if (note != null) {
            noteRepository.deleteById(noteId);
            return true;
        } else return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Note getNoteById(User user, Integer noteId) {
        return user.getNotes().get(noteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAllUserNotes(User user) {
        return user.getNotes();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getNotesByName(User user, String name) {
        return user.getNotes()
                .stream()
                .filter(note -> note.getNoteName().contains(name)).toList();
    }
}
