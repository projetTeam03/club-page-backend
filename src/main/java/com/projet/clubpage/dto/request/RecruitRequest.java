package com.projet.clubpage.dto.request;

import com.projet.clubpage.entity.Recruit;
import lombok.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




@Getter
@NoArgsConstructor //파라미터가 없는 기본 생성자 자동생성
public class RecruitRequest {

    private Integer progress;
    private Integer participants;
    private String duration;
    private List<Integer> position;
    private List<Integer> skill;
    private String endDate;
    private String github;
    private String title;
    private String contents;


    //디티오의 생성자
    @Builder
    public RecruitRequest(Integer progress, List<Integer> position, List<Integer> skill  ,Integer participants, String endDate, String github, String title, String contents, String duration) {
        this.progress = progress;
        this.endDate = endDate;
        this.github = github;
        this.title = title;
        this.participants = participants;
        this.contents = contents;
        this.position = position;
        this.skill = skill;
        this.duration = duration;

    }

    public static Timestamp convertToTimestamp(String inputDate) throws ParseException {
        // 입력받은 형식과 맞는 패턴을 지정합니다.
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 입력된 날짜 문자열을 Date 객체로 파싱합니다.
        java.util.Date date = inputFormat.parse(inputDate);

        // java.util.Date를 java.sql.Timestamp로 변환합니다.
        return new Timestamp(date.getTime());
    }


    public Recruit toEntity() throws ParseException {
        return Recruit.builder()
                .title(title)
                .contents(contents)
                .duration(duration)
                .progressMethod(progress)
                .participants(participants)
                .endDate(convertToTimestamp(endDate))
                .github(github)
                .build();
    }

}








