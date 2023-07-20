package com.projet.clubpage.entity;

import com.projet.clubpage.entity.embeddedId.RecruitPositionId;
import com.projet.clubpage.entity.embeddedId.RecruitTagId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "recruit_position")
@Getter
@Setter
public class RecruitPosition {

    @EmbeddedId
    private RecruitPositionId RecruitPositionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recruitId")
    @JoinColumn(name = "recruit_idx")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(name = "position_idx")
    private Position position;

}
