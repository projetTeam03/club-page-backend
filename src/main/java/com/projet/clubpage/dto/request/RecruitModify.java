package com.projet.clubpage.dto.request;

import com.projet.clubpage.entity.Position;
import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecruitModify {

    /* 특정 모집공고 수정 1 (불러오기) - participants, progress, duration, tag(skill), endDate, position, github, title, contents */
    private Integer participants;
    private Integer progressMethod;
    private String duration;
    private List<Position> position;
    private List<Tag> skill;
    private Timestamp endDate;
    private String contents;
    private String title;
    private String github;

    @Builder
    public RecruitModify(Recruit recruit, List<Position> listPosition, List<Tag> listTag) {
        this.participants = recruit.getParticipants();
        this.progressMethod = recruit.getProgressMethod();
        this.duration = recruit.getDuration();
        this.position = listPosition;
        this.skill = listTag;
        this.endDate = recruit.getEndDate();
        this.contents = recruit.getContents();
        this.title = recruit.getTitle();
        this.github = recruit.getGithub();
    }
}
