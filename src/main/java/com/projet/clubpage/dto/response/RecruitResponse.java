package com.projet.clubpage.dto.response;

import com.projet.clubpage.common.DateUtils;
import com.projet.clubpage.entity.Position;
import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.entity.Tag;
import com.projet.clubpage.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RecruitResponse {

    /*
   TODO 조회를 위한 응답Dto입니다!
    */

    /* 모집공고 리스트 조회(DTO 추가) - scraps, end_date, title, position, tags, user_nickname, views, state, deleteYn(쿼리로 처리, dto는 프론트단에서도 볼 수 있어서) */
    /* position, skill: 저장할 때는 <Integer>, 데이터 내려줄 때는 <Position>,<Tag> */

    private Long scraps;
    private String createDate;
    private String endDate;
    private String title;
    private List<Position> position;
    private List<Tag>skill;
    private Integer views;
    private Integer state;

    private String github;
    private User user;
//    private Integer userIdx;
//    private String deleteYn;




    @Builder
    public RecruitResponse(Recruit recruit, List<Position> listPosition, List<Tag> listTag, User user, Long scraps) throws ParseException {
        this.scraps = scraps;
        this.endDate = DateUtils.convertToString(recruit.getEndDate());
        this.createDate = DateUtils.convertToString(recruit.getCreateDate());
        this.title = recruit.getTitle();
        this.position = listPosition;
        this.skill = listTag;
        this.views = recruit.getViews();
        this.state = recruit.getState();
        this.github = recruit.getGithub();
        this.user = user;
    }






}


