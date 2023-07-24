package com.projet.clubpage.dto.response;

import lombok.*;

import java.time.LocalDateTime;

// controller와 service 간에 주고받을 객체를 정의하며, 최종적으로는 view에 뿌려줄 객체
// Entity와 속성이 같을 수 있으나, 여러 service를 거쳐야 하는 경우 dto의 몸집은 더 커짐
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectResponse {
    private Integer idx;
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
    private LocalDateTime createdDate;
    private Integer mainCategoryIdx;
    private Integer subCategoryIdx;
    private Integer userIdx;
    private Integer views;
    private Integer likes;
    private Boolean deleteYn;
    private LocalDateTime modifiedDate;

    public com.projet.clubpage.entity.Project toEntity() {
        com.projet.clubpage.entity.Project build = com.projet.clubpage.entity.Project.builder()
                .idx(idx)
                .title(title)
                .image(image)
                .youtube(youtube)
                .content(content)
                .teamMember(teamMember)
                .teamName(teamName)
                .github(github)
                .startDate(startDate)
                .endDate(endDate)
                .distribution(distribution)
                .createdDate(createdDate)
                .mainCategoryIdx(mainCategoryIdx)
                .subCategoryIdx(subCategoryIdx)
                .userIdx(userIdx)
                .views(views)
                .likes(likes)
                .deleteYn(deleteYn)
                .modifiedDate(modifiedDate)
                .build();
        return build;
    }

    @Builder
    public ProjectResponse(Integer id, String title, String image, String youtube,
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