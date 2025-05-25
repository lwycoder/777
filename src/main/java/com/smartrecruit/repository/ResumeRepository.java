// ResumeRepository.java
package com.smartrecruit.repository;

import com.smartrecruit.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    Resume findByUserId(Integer userId);
}