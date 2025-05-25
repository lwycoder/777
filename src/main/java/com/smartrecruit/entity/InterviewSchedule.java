// InterviewSchedule.java
package com.smartrecruit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "interview_schedules")
@Data
public class InterviewSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_ad_id")
    private JobAd jobAd;

    @Temporal(TemporalType.TIMESTAMP)
    private Date scheduleTime;

    private String location;

    @Column(columnDefinition = "tinyint default 0")
    private Integer status; // 0=待确认，1=已确认，2=已取消，3=已完成

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private Integer score;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}