// JobApplication.java
package com.smartrecruit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "job_applications")
@Data
public class JobApplication {
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
    @Column(updatable = false)
    private Date applyTime;

    @Column(columnDefinition = "tinyint default 0")
    private Integer status; // 0=已申请，1=已查看，2=已拒绝，3=已邀请面试

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}