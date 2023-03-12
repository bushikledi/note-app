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
    public boolean newNote(Note note, User user) {
        try {
            note.setCreatedDate(LocalDate.now());
            note.setEditedDate(LocalDate.now());
            note.setUserId(user.getUserId());
            noteRepository.save(note);
            noteRepository.save(note);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    @Transactional
    public boolean editNote(Integer noteId, Note modNote, User user) {
        try {
            Note note = noteRepository.findByNoteIdAndUserId(noteId, user.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Note not found for ID "
                            + noteId + " and user ID " + user.getUserId()));
            note.setNote(modNote.getNote());
            note.setNoteName(modNote.getNoteName());
            note.setEditedDate(LocalDate.now());
            noteRepository.save(note);
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    @Override
    @Transactional
    public boolean deleteNote(Integer noteId, User user) {
        try {
            noteRepository.deleteByNoteIdAndUserId(noteId, user.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Note not found for ID "
                            + noteId + " and user ID " + user.getUserId()));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Note getNoteById(User user, Integer noteId) {
        return noteRepository.findByNoteIdAndUserId(noteId, user.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAllUserNotes(User user) {
        return noteRepository.findByUserIdOrderByEditedDate(user.getUserId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getNotesByName(User user, String name) {
        return noteRepository.findByUserIdAndNoteNameContainingIgnoreCaseOrderByNoteName(user.getUserId(), name);
    }
}
