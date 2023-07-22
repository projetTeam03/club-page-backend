package com.projet.clubpage.dto.response;

import com.projet.clubpage.entity.Position;
import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.entity.Tag;
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

    /* 모집공고 리스트 조회(DTO 추가) - scraps, end_date, title, position(0~3개까지 보여줌), tags(0~5개까지 보여줌), user_nickname, views, state(?), deleteYn(쿼리로 처리, dto는 프론트단에서도 볼 수 있어서) */
    /* position, skill: 저장할 때는 <Integer>, 데이터 내려줄 때는 <Position>,<Tag> */

    private Integer scraps;
    private Timestamp endDate;
    private String title;
    private List<Position> position;
    private List<Tag>skill;
    private Integer views;
    private Integer state;
//    private Integer userIdx; //(?) user_nickname?
//    private String deleteYn;




    @Builder
    public RecruitResponse(Recruit recruit, List<Position> listPosition, List<Tag> listTag) {
        this.scraps = recruit.getScraps();
        this.endDate = recruit.getEndDate();
        this.title = recruit.getTitle();
        this.position = listPosition;
        this.skill = listTag;
        this.views = recruit.getViews();
        this.state = recruit.getState();
    }






}


