package com.imentor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imentor.dto.MentorDTO;
import com.imentor.model.Mentor;
import com.imentor.service.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final MentorService mentorService;

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody MentorDTO mentorDTO) {
        Mentor createdMentor = mentorService.createMentor(mentorDTO);
        return ResponseEntity.ok(createdMentor);
    }

    @GetMapping
    public ResponseEntity<List<MentorDTO>> getAllMentors() {
        List<MentorDTO> mentors = mentorService.getAllMentors();
        return ResponseEntity.ok(mentors);
    }
}
