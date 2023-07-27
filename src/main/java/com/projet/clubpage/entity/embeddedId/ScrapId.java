package com.projet.clubpage.entity.embeddedId;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ScrapId implements Serializable {

    @Column(name = "user_idx")
    private Long userId;

    @Column(name = "recruit_idx")
    private Integer recruitId;
}
