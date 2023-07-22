package com.projet.clubpage.entity;

import com.projet.clubpage.entity.embeddedId.PositionTagId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "position_tag")
@Getter
@Setter
public class PositionTag {

    @EmbeddedId
    private PositionTagId positionTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(name = "position_idx")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_idx")
    private Tag tag;

}
