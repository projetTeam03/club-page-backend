package com.projet.clubpage.entity;

import com.projet.clubpage.dto.RecruitDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name="recruit")
@EntityListeners(AuditingEntityListener.class)
public class Recruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값의 생성을 DB에게 위임한다.
    @Column(name = "idx") //auto-increment 로 자동으로 올라가는 값
    private Integer idx;

    @Column(name = "title")
    private String title;   //Varchar - String

    @Column(name = "contents")
    private String contents;

    @Column(name="duration")
    private String duration;

    @Column(name = "progress_method")
    private Integer progress_method;   //(진행방식) 온라인 0, 오프라인 1

    @Column(name = "participants")
    private Integer participants;

    @Column(name="scraps")
    private Integer scraps; //처음은 무조건 0, setScraps(0)

    @Column(name = "views")
    private Integer views; //처음은 무조건 0

    @Column(name = "user_idx")
    private Integer user_idx;

    @Column(name="state")
    private Integer state; //진행중 0, 마감1

    @Column(name="deleteYn")  //Y면 삭제 O, N 삭제 X , 프론트에 "N"인것 만 보여주기
    private String deleteYn;

    @CreationTimestamp //생성되었을 때 시간을 자동으로 만들어준다
    @Column(name="create_date", updatable = false) // 수정 시에는 관여를 안 하게끔.
    private LocalDateTime create_date;

    @CreationTimestamp
    @Column(name="end_date", insertable = false) //인서트를 할 때는 관여를 안 하게끔.
    private LocalDateTime end_date; //모집 마감일



//    public static Recruit toSaveEntity(RecruitDTO recruitDTO) {
//        Recruit recruit = new Recruit(); //엔티티 인스턴스화
//
//        recruit.setIdx(recruitDTO.getIdx());
//        recruit.setTitle(recruitDTO.getTitle());
//        recruit.setContents(recruitDTO.getContents());
////        recruit.setProgress_method(recruitDTO.getProgress_method());
////        recruit.setPartcipants(recruitDTO.getPartcipants());
////        recruit.setViews(0);
////        recruit.setUser_idx(recruitDTO.getUser_idx());
//        return recruit;
//  }


}





