package com.projet.clubpage.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Project extends Timestamp {

    @Id
    @GeneratedValue
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

    @Column(length = 300, nullable = false)
    private String github;

    @Column(length = 10, nullable = false)
    private String startDate;

    @Column(length = 10, nullable = false)
    private String endDate;

    @Column(length = 300, nullable = false)
    private String distribution;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Column
    private Integer mainCategoryIdx;

    @Column
    private Integer subCategoryIdx;

    @Column
    private Integer userIdx;

    @Column
    private Integer views;

    @Column
    private Integer likes;

    @Column
    private Boolean deleteYn;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Project(Integer idx, String title, String image, String youtube,
                   String content, String teamMember, String teamName,
                   String github, String startDate, String endDate, String distribution,
                   LocalDateTime createdDate, Integer mainCategoryIdx, Integer subCategoryIdx, Integer userIdx,
                   Integer views, Integer likes, Boolean deleteYn, LocalDateTime modifiedDate
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
        this.createdDate = createdDate;
        this.mainCategoryIdx = mainCategoryIdx;
        this.subCategoryIdx = subCategoryIdx;
        this.userIdx = userIdx;
        this.views = views;
        this.likes = likes;
        this.deleteYn = deleteYn;
        this.modifiedDate = modifiedDate;
    }
}
