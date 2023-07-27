package com.projet.clubpage.entity.embeddedId;

import com.projet.clubpage.entity.Position;
import com.projet.clubpage.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class RecruitTagId implements Serializable {

    private static final long serialVersionUID = 4553612403725486229L;
    @Column(name = "recruit_idx")
    private Integer recruitId;

    @Column(name = "tag_stack_idx")
    private Integer tagId;
}
