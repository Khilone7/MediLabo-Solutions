package com.medilabo.microservicefront.controller;

import com.medilabo.microservicefront.entity.Patient;
import com.medilabo.microservicefront.service.AssessmentService;
import com.medilabo.microservicefront.service.NoteService;
import com.medilabo.microservicefront.service.PatientService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final NoteService noteService;
    private final AssessmentService assessmentService;

    @GetMapping
    public String getAllPatients(Model model, HttpSession session) {
        model.addAttribute("patients", patientService.getAllPatients(session));
        return "patients";
    }

    @GetMapping("/{id}")
    public String getPatientById(Model model, HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("patient", patientService.getPatientById(id, session));
            model.addAttribute("notes", noteService.getNotesByPatientId(id, session));
            model.addAttribute("risk", assessmentService.getAssessmentByPatientId(id, session));
            return "patient-detail";
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Patient introuvable.");
            return "redirect:/patients";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @GetMapping("/{id}/edit")
    public String editPatient(Model model, HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("patient", patientService.getPatientById(id, session));
            return "patient-form";
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Patient introuvable.");
            return "redirect:/patients";
        }
    }

    @PostMapping("/add")
    public String createPatient(HttpSession session, @ModelAttribute Patient patient, Model model) {
        try {
            patientService.createPatient(patient, session);
            return "redirect:http://localhost:8080/patients";
        } catch (HttpClientErrorException e) {
            model.addAttribute("errorMessage", e.getResponseBodyAsString());
            return "patient-form";
        }
    }

    @PostMapping("/{id}/edit")
    public String updatePatient(HttpSession session, @PathVariable Long id, @ModelAttribute Patient patient, Model model) {
        try {
            patientService.updatePatient(id, patient, session);
            return "redirect:http://localhost:8080/patients";
        } catch (HttpClientErrorException e) {
            model.addAttribute("errorMessage", e.getResponseBodyAsString());
            return "patient-form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(HttpSession session, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            patientService.deletePatient(id, session);
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Patient introuvable.");
        }
        return "redirect:/patients";
    }
}
