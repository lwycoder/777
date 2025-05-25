// ResumeService.java
package com.smartrecruit.service;

import com.smartrecruit.entity.Resume;
import com.smartrecruit.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume findByUserId(Integer userId) {
        return resumeRepository.findByUserId(userId);
    }

    public Resume save(Resume resume) {
        return resumeRepository.save(resume);
    }
}
