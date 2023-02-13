package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.NoteRepository;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NoteServicesImplementation implements NoteServices {

    private NoteRepository noteRepository;
    private UserRepository userRepository;

    @Override
    public void newNote(Integer id, Note note) {
        try {
            User user = userRepository.findById(id).get();
            note.setUser(user);
            note.setCreatedDate(LocalDate.now());
            note.setEditedDate(LocalDate.now());
            noteRepository.save(note);
        } catch (Exception e) {
        }

    }

    @Override
    public void editNote(Integer note_id,Note note) {
        Note note1  = noteRepository.findById(note_id).get();
        note1.setNoteName(note.getNoteName());
        note.setNote(note.getNote());
        note1.setEditedDate(LocalDate.now());
        noteRepository.save(note1);
    }

    @Override
    public boolean deleteNote(Integer id) {
        try {
            noteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Note getNoteById(Integer id) {
        return noteRepository.findById(id).get();
    }
}
