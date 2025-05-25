// JobAd.java
package com.smartrecruit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job_ads")
@Data
public class JobAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String salaryRange;
    private String location;

    private Date deadline;

    @Column(columnDefinition = "tinyint default 0")
    private Integer status; // 0=发布中，1=已结束，2=已暂停

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "jobAd")
    private List<JobApplication> applications;
}
