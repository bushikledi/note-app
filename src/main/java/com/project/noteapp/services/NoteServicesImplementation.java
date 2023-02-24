package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.model.User;
import com.project.noteapp.repository.NoteRepository;
import com.project.noteapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServicesImplementation implements NoteServices {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void newNote(Integer id, Note note) {
        User user = userRepository.findById(id).get();
        note.setUser(user);
        note.setCreatedDate(LocalDate.now());
        note.setEditedDate(LocalDate.now());
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public void editNote(Integer note_id, Note note) {
        note.setNoteId(note_id);
        note.setEditedDate(LocalDate.now());
        noteRepository.save(note);
    }

    @Override
    @Transactional
    public boolean deleteNote(Integer id) {
        try {
            noteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Note getNoteById(Integer id) {
        return noteRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAllUserNotes(Integer id) {
        return noteRepository.findAll()
                .stream()
                .filter(note -> note.getUser().getUserId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getNotesByName(Integer user_id, String name) {
        return noteRepository.findAll()
                .stream()
                .filter(note -> note.getUser().getUserId().equals(user_id) && note.getNoteName().contains(name))
                .collect(Collectors.toList());
    }
}
