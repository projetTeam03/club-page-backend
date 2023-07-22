package com.projet.clubpage.dto.response;

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
public class RecruitDetail {

    /* 특정 모집공고 상세조회 - title, [user_nickname], create_date, progress_method,
    participants, duration, position, tag, contents, views, scraps, [comments]
     */

    private String title;
    private Timestamp createDate;
    private Integer progressMethod;
    private Integer participants;
    private String duration;
    private List<Position> position;
    private List<Tag> skill;
    private String contents;
    private Integer views;
    private Integer scraps;


    @Builder
    public RecruitDetail(Recruit recruit, List<Position> listPosition, List<Tag> listTag) {
        this.title = recruit.getTitle();
        this.createDate = recruit.getCreateDate();
        this.progressMethod = recruit.getScraps();
        this.participants = recruit.getParticipants();
        this.duration = recruit.getDuration();
        this.position = listPosition;
        this.skill = listTag;
        this.contents = recruit.getContents();
        this.views = recruit.getViews();
        this.scraps = recruit.getScraps();
    }




}
