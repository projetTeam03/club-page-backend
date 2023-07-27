package com.projet.clubpage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Column(name = "value", length = 45)
    private String value;

    @Column(name = "url", length = 300)
    private String url;

    @Column(name = "label", length = 45)
    private String label;

//    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private Set<PositionTag> position = new HashSet<>();



}