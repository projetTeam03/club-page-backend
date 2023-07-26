package com.projet.clubpage.dto.response;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectDetailResponse {


    private String title;
    private String image;
    private String youtube;
    private String content;
    private String teamMember;
    private String teamName;
    private String github;
    private Timestamp startDate;
    private Timestamp endDate;
    private String distribution;
    private Integer views;
    private Integer likes;
    private Integer userIdx;

    @Builder
    public ProjectDetailResponse( String title, String image, String youtube,
                                 String content, String teamMember, String teamName,
                                 String github, Timestamp startDate, Timestamp endDate, String distribution,
                                  Integer views,Integer likes, Integer userIdx
      ) {
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
        this.views = views;
        this.likes = likes;
        this.userIdx = userIdx;
    }


}