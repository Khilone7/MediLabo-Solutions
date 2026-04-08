package com.medilabo.microservicenotes.controller;

import com.medilabo.microservicenotes.entity.Note;
import com.medilabo.microservicenotes.entity.dto.NoteRequest;
import com.medilabo.microservicenotes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{patientId}")
    public ResponseEntity<List<Note>> getNotesByPatientId(@PathVariable Long patientId) {
        List<Note> notes = noteService.getNotesByPatientId(patientId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<Void> createNote(@PathVariable Long patientId,@Valid @RequestBody NoteRequest noteRequest) {
        noteService.createNote(patientId, noteRequest.content());
        return ResponseEntity.created(URI.create("/api/notes/" + patientId)).build();
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable String noteId) {
        noteService.deleteNoteById(noteId);
        return ResponseEntity.noContent().build();
    }
}
