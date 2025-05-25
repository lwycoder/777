// JobAdRepository.java
package com.smartrecruit.repository;

import com.smartrecruit.entity.JobAd;
import com.smartrecruit.entity.JobApplication;
import com.smartrecruit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobAdRepository extends JpaRepository<JobAd, Integer> {
    List<JobAd> findByAdmin(User admin);

    long countByAdmin(User admin);

    long countByAdminAndStatus(User admin, Integer status);

    @Query("SELECT COUNT(a) FROM JobApplication a JOIN a.jobAd j WHERE j.admin = :admin")
    long countApplicationsByAdmin(@Param("admin") User admin);
    // 统计待处理申请数量


}