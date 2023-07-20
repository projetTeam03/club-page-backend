package com.projet.clubpage.dto;

import com.projet.clubpage.entity.Recruit;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor //파라미터가 없는 기본 생성자 자동생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 자동생생
public class RecruitDTO { //계층(데이터베이스, 컨트롤러, 엔티티) 사이의 데이터 교환을 위한 객체.

    private Integer idx;
    private String title;
    private String contents;



//    private String progress_method;
//
//    private Integer partcipants;
//
//    private Integer views;
//
//    private Integer user_idx;

//    private LocalDateTime create_date;

}



//    public RecruitDTO(Integer idx, String title, String contents) {
//        this.idx = idx;
//        this.title = title;
//        this.contents = contents; }


//        this.progress_method = progress_methods;
//        this.partcipants = partcipants;
//        this.views = views;
//        this.user_idx = user_idx;
//        this.create_date = create_date;




