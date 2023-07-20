package com.projet.clubpage.dto;

import com.projet.clubpage.entity.Recruit;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;


//DTO: 계층(데이터베이스, 컨트롤러, 엔티티) 사이의 데이터 교환을 위한 객체. Entity 를 요청과 응답에 사용한다면 응답시 필요하지 않은 속성도 함께 보내진다. 하지만 특정 API에서 필요한 DTO를 별도로 만들면 '해당 API에서 필요한 필드들로만 구성하여' 응답 객체를 만들 수 있다.

@Getter
@Setter
@ToString
@NoArgsConstructor //파라미터가 없는 기본 생성자 자동생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 자동생생
public class RecruitDTO {

    private Integer idx;
    private String title;
    private String contents;
    private String duration;
    private Integer progress_method;
    private Integer participants;
    private Integer scraps;
    private Integer views;
    private Integer user_idx;
    private Integer state;
    private String deleteYn;
    private LocalDateTime create_date;
    private LocalDateTime end_date;

//    public RecruitDTO(Integer idx, String title, String contents, String duration, Integer progress_method, Integer participants, Integer scraps, Integer views, Integer user_idx, Integer state, String deleteYn, LocalDateTime create_date, LocalDateTime end_date){
//        this.idx = idx;
//        this.title = title;
//        this.contents = contents;
//        this.duration = duration;
//        this.progress_method = progress_method;
//        this.participants = participants;
//        this.scraps = scraps;
//        this.views = views;
//        this.create_date = create_date;
//        this.user_idx = user_idx;
//        this.state = state;
//        this.end_date = end_date;
//        this.deleteYn = deleteYn;
//    }


    public static RecruitDTO toRecruitDTO(Recruit recruit) {
        RecruitDTO recruitDTO = new RecruitDTO();

        recruitDTO.setIdx(recruit.getIdx());
        recruitDTO.setTitle(recruit.getTitle());
        recruitDTO.setContents(recruit.getContents());
        recruitDTO.setDuration(recruit.getDuration());
        recruitDTO.setProgress_method(recruit.getProgress_method());
        recruitDTO.setParticipants(recruit.getParticipants());
        recruitDTO.setScraps(recruit.getScraps());
        recruitDTO.setViews(recruit.getViews());
        recruitDTO.setCreate_date(recruit.getCreate_date());
        recruitDTO.setUser_idx(recruit.getUser_idx());
        recruitDTO.setState(recruit.getState());
        recruitDTO.setEnd_date(recruit.getEnd_date());
        recruitDTO.setDeleteYn(recruit.getDeleteYn());

        return recruitDTO;
    }
}








