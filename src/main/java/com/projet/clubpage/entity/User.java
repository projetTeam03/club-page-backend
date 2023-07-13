package com.projet.clubpage.entity;

import com.projet.clubpage.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private int deleted;

    @Column(name = "create_at")
    private Timestamp createDt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long idx, String email, String name, int deleted, Timestamp createDt, Role role) {
        this.idx = idx;
        this.email = email;
        this.name = name;
        this.deleted = deleted;
        this.createDt = createDt;
        this.role = role;
    }

    public User update(OAuthAttributes attributes) {
        this.name = attributes.getName();
        this.email = attributes.getEmail();
        this.role = Role.USER;

        return this;
    }
}
