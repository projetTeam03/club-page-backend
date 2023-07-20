package com.projet.clubpage.entity;

import com.projet.clubpage.entity.embeddedId.RecruitTagId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "recruit_tag")
@Getter
@Setter
public class RecruitTag {
    @EmbeddedId
    private RecruitTagId recruitTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recruitId")
    @JoinColumn(name = "recruit_idx")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_stack_idx")
    private Tag tag;

}
