package com.imentor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.imentor.dto.MentorDTO;
import com.imentor.model.Mentor;
import com.imentor.repository.MentorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    public Mentor createMentor(MentorDTO mentorDTO) {
        Mentor mentor = Mentor.builder()
                .name(mentorDTO.getName())
                .branch(mentorDTO.getBranch())
                .description(mentorDTO.getDescription())
                .systemPrompt(mentorDTO.getSystemPrompt())
                .mentorAvatar(mentorDTO.getMentorAvatar())
                .lessons(mentorDTO.getLessons())
                .build();
        return mentorRepository.save(mentor);
    }

    public List<MentorDTO> getAllMentors() {
        return mentorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MentorDTO convertToDTO(Mentor mentor) {
        MentorDTO dto = new MentorDTO();
        dto.setName(mentor.getName());
        dto.setBranch(mentor.getBranch());
        dto.setMentorAvatar(mentor.getMentorAvatar());
        dto.setDescription(mentor.getDescription());
        dto.setSystemPrompt(mentor.getSystemPrompt());
        dto.setLessons(mentor.getLessons());
        return dto;
    }
}
