package com.projet.clubpage.entity.embeddedId;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RecruitPositionId implements Serializable {

    @Column(name = "position_idx")
    private Integer positionId;

    @Column(name = "recruit_idx")
    private Integer recruitId;

}
