package com.projet.clubpage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "human")
public class Human {    //엔티티 = 데이터베이스 같은 곳
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idx")
    private Integer idx;

    @Column(name="name")
    private String name;



}
