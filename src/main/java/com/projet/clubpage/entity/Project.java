package com.projet.clubpage.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;


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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 100, nullable = false)
    private String teamMember;

    @Column(length = 30, nullable = false)
    private String teamName;

    @Column(length = 300)
    private String github;

    @Column(length = 10, nullable = false)
    private Timestamp startDate;

    @Column(length = 10, nullable = false)
    private Timestamp endDate;

    @Column(length = 300, nullable = false)
    private String distribution;

//    @CreationTimestamp
//    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm", timezone = "Asia/Seoul")
//    @Column(updatable = false)
//    private Timestamp createdDate;

    @Column
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
                   String content, String teamMember, String teamName,
                   String github, Timestamp startDate, Timestamp endDate, String distribution,
                    Integer userIdx, Integer views, Integer likes, String deleteYn
                         ) {
        this.idx = idx;
        this.title = title;
        this.image = image;
        this.youtube = youtube;
        this.content = content;
        this.teamMember = teamMember;
        this.teamName = teamName;
        this.github = github;
        this.startDate = startDate;
        this.endDate = endDate;
        this.distribution = distribution;
        this.userIdx = userIdx;
        this.views = views;
        this.likes = likes;
        this.deleteYn = deleteYn;
    }

    public void setTitle(String title) {
    }


}
