package com.projet.clubpage.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "idx", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @Column(name = "nickname", nullable = false, length = 45)
    private String nickname;

    @Column(name = "create_at")
    @CreationTimestamp
    private Timestamp createAt;

    @Lob
    @Column(name = "profile_picture")
    private String profilePicture;

    public User() {

    }
}