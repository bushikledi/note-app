package com.project.noteapp.repository;

import com.project.noteapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    Optional<Note> findByNoteIdAndUserId(Integer noteId, Integer userId);

    Optional<Note> deleteByNoteIdAndUserId(Integer noteId, Integer userId);

    List<Note> findByUserIdOrderByEditedDate(Integer userId);

    List<Note> findByUserIdAndNoteNameContainingIgnoreCaseOrderByNoteName(Integer userId, String name);
}
