package com.project.noteapp.services;

import com.project.noteapp.model.Note;
import com.project.noteapp.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoteServicesImplementation implements NoteServices{

    private NoteRepository noteRepository;

    @Override
    public void newNote(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void editNote(Note note) {
        noteRepository.save(note);
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
    public Note getNoteById(Integer id){
        return noteRepository.findById(id).get();
    }
}
