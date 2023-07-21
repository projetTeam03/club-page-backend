package com.projet.clubpage.entity;

import com.projet.clubpage.dto.response.RecruitResponse;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Table(name="recruit")
@NoArgsConstructor
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
    private Integer progressMethod;   //(진행방식) 온라인 0, 오프라인 1

    @Column(name = "participants")
    private Integer participants;

    @Column(name="scraps")
    private Integer scraps; //처음은 무조건 0, setScraps(0)

    @Column(name = "views")
    private Integer views; //처음은 무조건 0

    @Column(name = "user_idx")
    private Integer userIdx;

    @Column(name="state")
    private Integer state; //진행중 0, 마감1

    @Column(name="delete_yn")  //Y면 삭제 O, N 삭제 X , 프론트에 "N"인것 만 보여주기
    private String deleteYn;

    @CreationTimestamp //생성되었을 때 시간을 자동으로 만들어준다
    @Column(name="create_date", updatable = false) // 수정 시에는 관여를 안 하게끔.
    private Timestamp createDate;

    @Column(name="end_date", insertable = false) //인서트를 할 때는 관여를 안 하게끔.
    private Timestamp endDate; //모집 마감일

    @Column(name = "github", length = 200)
    private String github;



    @Builder
    public Recruit(String title, String contents, String duration, Integer progressMethod, Integer participants, Timestamp endDate, String github) {
        this.title = title;
        this.contents = contents;
        this.duration = duration;
        this.progressMethod = progressMethod;
        this.participants = participants;
        this.scraps = 0;
        this.views = 0;
        this.userIdx = 0;
        this.state = 0;
        this.deleteYn = "N";
        this.endDate = endDate;
        this.github = github;

    }

    public RecruitResponse toDto(Recruit recruit, List<Position> listPosition, List<Tag> listTag) {
        return RecruitResponse.builder()
                .recruit(recruit)
                .listTag(listTag)
                .listPosition(listPosition)
                .build();
    }




}





