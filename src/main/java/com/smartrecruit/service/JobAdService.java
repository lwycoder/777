// JobAdService.java
package com.smartrecruit.service;

import com.smartrecruit.entity.JobAd;
import com.smartrecruit.entity.JobApplication;
import com.smartrecruit.entity.User;
import com.smartrecruit.repository.JobAdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdService {

    private final JobAdRepository jobAdRepository;

    public JobAdService(JobAdRepository jobAdRepository) {
        this.jobAdRepository = jobAdRepository;
    }

    public JobAd save(JobAd jobAd) {
        return jobAdRepository.save(jobAd);
    }

    public List<JobAd> findAllByAdmin(User admin) {
        return jobAdRepository.findByAdmin(admin);
    }

    public long countByAdmin(User admin) {
        return jobAdRepository.countByAdmin(admin);
    }

    public long countActiveJobsByAdmin(User admin) {
        return jobAdRepository.countByAdminAndStatus(admin, 0);
    }

    public long countApplicationsByAdmin(User admin) {
        return jobAdRepository.countApplicationsByAdmin(admin);
    }

}