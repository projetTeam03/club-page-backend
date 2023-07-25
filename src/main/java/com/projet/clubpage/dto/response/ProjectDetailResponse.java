package com.projet.clubpage.dto.response;

import lombok.*;

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
    private String startDate;
    private String endDate;
    private String distribution;
    private Integer mainCategoryIdx;
    private Integer subCategoryIdx;
    private Integer views;
    private Integer likes;

    @Builder
    public ProjectDetailResponse( String title, String image, String youtube,
                                 String content, String teamMember, String teamName,
                                 String github, String startDate, String endDate, String distribution,
                                  Integer mainCategoryIdx, Integer subCategoryIdx,Integer views,Integer likes
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
        this.mainCategoryIdx = mainCategoryIdx;
        this.subCategoryIdx = subCategoryIdx;
        this.views = views;
        this.likes = likes;
    }


}