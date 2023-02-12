package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoteServices {

    private NoteRepository noteRepository;

    public void newNote(Note note) {
        noteRepository.save(note);
    }

    public void editNote(Note note) {
        noteRepository.save(note);
    }

    public boolean deleteNote(Integer id) {
        try {
            noteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Note getNoteById(Integer id){
        return noteRepository.findById(id).get();
    }
}
