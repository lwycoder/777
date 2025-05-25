// User.java
package com.smartrecruit.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(columnDefinition = "tinyint default 0")
    private Integer role; // 0=普通用户，1=管理员

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createTime;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Resume resume;
}