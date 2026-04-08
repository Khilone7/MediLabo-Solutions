package com.medilabo.microservicefront.controller;

import com.medilabo.microservicefront.service.NoteService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/patients/{patientId}/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public String createNote(@PathVariable Long patientId, @RequestParam String content, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            noteService.createNote(patientId, content, session);
            return "redirect:http://localhost:8080/patients/" + patientId;
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getResponseBodyAsString());
            return "redirect:/patients/" + patientId;
        }
    }

    @PostMapping("/{noteId}/delete")
    public String deleteNote(@PathVariable Long patientId, @PathVariable String noteId, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            noteService.deleteNoteById(noteId, session);
            return "redirect:http://localhost:8080/patients/" + patientId;
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Note introuvable.");
            return "redirect:/patients/" + patientId;
        }
    }
}
