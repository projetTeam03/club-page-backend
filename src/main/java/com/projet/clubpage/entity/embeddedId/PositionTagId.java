package com.projet.clubpage.entity.embeddedId;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class PositionTagId implements Serializable {

    @Column(name = "position_idx")
    private Integer positionId;

    @Column(name = "tag_idx")
    private Integer tagId;


}
