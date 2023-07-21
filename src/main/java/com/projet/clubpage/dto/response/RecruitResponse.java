package com.projet.clubpage.dto.response;

import com.projet.clubpage.entity.Recruit;
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

    //모집공고 리스트 조회(DTO 추가) - scraps, end_date, title, position(0~3개까지 보여줌), tags(0~5개까지 보여줌), user_nickname, views, state(?), deleteYn(?)

    private Integer scraps;
    private Timestamp endDate;
    private String title;
    private List<Integer> position;
    private List<Integer>skill;
    private Integer views;
//    private Integer userIdx; //(?) user_nickname?
//    private Integer state;
//    private String deleteYn;




    @Builder
    public RecruitResponse(Recruit recruit) {
        this.scraps = scraps;
        this.endDate = endDate;
        this.title = title;
        this.position = position;
        this.skill = skill;
        this.views = views;
    }






}


