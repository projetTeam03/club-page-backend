package com.projet.clubpage.dto.request;

import com.projet.clubpage.entity.Project;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


// controller와 service 간에 주고받을 객체를 정의하며, 최종적으로는 view에 뿌려줄 객체
// Entity와 속성이 같을 수 있으나, 여러 service를 거쳐야 하는 경우 dto의 몸집은 더 커짐
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectRequest {

    public static Timestamp convert(Date date) {
        Date utilDate = new java.util.Date();
        Timestamp sqlTimeStamp = convert(utilDate);
        return sqlTimeStamp;
    }

    private String title;
    private String image;
    private String youtube;
    private Timestamp startDate;
    private Timestamp endDate;
    private String content;
    private String distribution;
    private String teamName;
    private String teamMember;
    private String github;
    private Integer userIdx;


    public Project toEntity() {
        Project build = Project.builder()
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
                .userIdx(userIdx)
                .build();
        return build;
    }

    @Builder
    public ProjectRequest(String title, String image, String youtube,
                          String content, String teamMember, String teamName,
                          String github, Timestamp startDate, Timestamp endDate, String distribution,
                          Integer userIdx
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
        this.userIdx = userIdx;

    }

    public void addAttribute(String list, List<ProjectRequest> projectRequestList) {
    }
}