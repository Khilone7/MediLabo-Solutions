package com.medilabo.microservicefront.service;

import com.medilabo.microservicefront.configuration.RestTemplateConfig;
import com.medilabo.microservicefront.configuration.UrlConfig;
import com.medilabo.microservicefront.entity.Note;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final RestTemplateConfig restTemplateConfig;
    private final UrlConfig urlConfig;

    public List<Note> getNotesByPatientId(Long patientId, HttpSession session) {
        Note[] notes = restTemplateConfig.restTemplate(session).getForObject(urlConfig.getNotes() + "/" + patientId, Note[].class);
        return notes == null ? List.of() : List.of(notes);
    }

    public void createNote(Long patientId, String content, HttpSession session) {
        Map<String, String> request = new HashMap<>();
        request.put("content", content);
        restTemplateConfig.restTemplate(session).postForObject(urlConfig.getNotes() + "/" + patientId, request, Note.class);
    }

    public void deleteNoteById(String noteId, HttpSession session) {
        restTemplateConfig.restTemplate(session).delete(urlConfig.getNotes() + "/" + noteId);
    }
}
