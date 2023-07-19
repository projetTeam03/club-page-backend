package com.projet.clubpage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "position")
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "values", length = 45)
    private String value;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private Set<PositionTag> tag = new HashSet<>();

}