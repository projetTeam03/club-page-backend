package com.projet.clubpage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;

import static com.projet.clubpage.dto.request.ProjectRequest.convertToTimestamp;


@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Project{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String image;

    @Column(length = 300, nullable = false)
    private String youtube;

    @Column(nullable = false)
    private String contents;

    @Column(name="team_member",length = 100, nullable = false)
    private String teamMember;

    @Column(name="team_name",length = 30, nullable = false)
    private String teamName;

    @Column(length = 300)
    private String github;

//    @Temporal(value = TemporalType.TIMESTAMP)
//    @CreationTimestamp
    @Column(name="start_date")
    private Timestamp startDate;

//    @Temporal(value = TemporalType.TIMESTAMP)
//    @CreationTimestamp
    @Column(name="end_date")
    private Timestamp endDate;

    @Column(length = 300, nullable = false)
    private String distribution;

//    @CreationTimestamp
//    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm", timezone = "Asia/Seoul")
//    @Column(updatable = false)
//    private Timestamp createdDate;

    @Column(name="user_idx")
    private Integer userIdx;

    @Column
    private Integer views;

    @Column
    private Integer likes;

    @Column
    private String deleteYn;

//    @LastModifiedDate
//    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss", timezone = "Asia/Seoul")
//    private Timestamp modifiedDate;

    @Builder
    public Project(Integer idx, String title, String image, String youtube,
                   String contents, String teamMember, String teamName,
                   String github, Timestamp startDate, Timestamp endDate, String distribution,
                    Integer userIdx, Integer views, Integer likes, String deleteYn
                         ) {
        this.idx = idx;
        this.title = title;
        this.image = image;
        this.youtube = youtube;
        this.contents = contents;
        this.teamMember = teamMember;
        this.teamName = teamName;
        this.github = github;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distribution = distribution;
        this.userIdx = userIdx;
        this.views = 0;
        this.likes = 0 ;
        this.deleteYn = "N";
    }

    public Project toDto() throws ParseException {
        Project build = Project.builder()
                .title(title)
                .image(image)
                .youtube(youtube)
                .contents(contents)
                .teamMember(teamMember)
                .teamName(teamName)
                .github(github)
                .startDate(startDate)
                .endDate(endDate)
                .distribution(distribution)
                .userIdx(userIdx)
                .build();
        return build;
    }
}
