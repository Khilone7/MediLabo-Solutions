package com.medilabo.microservicenotes.service;

import com.medilabo.microservicenotes.entity.Note;
import com.medilabo.microservicenotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public void createNote(Long patientId, String content) {
        var note = new Note();
        note.setPatientId(patientId);
        note.setContent(content);
        note.setDate(LocalDate.now());
        noteRepository.save(note);
    }

    public List<Note> getNotesByPatientId(Long patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    public void deleteNoteById(String noteId) {
        if (!noteRepository.existsById(noteId)) {
            throw new NoSuchElementException("Note introuvable");
        }
        noteRepository.deleteById(noteId);
    }
}
