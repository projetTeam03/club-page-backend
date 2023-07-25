package com.projet.clubpage.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResponse {

    public static Timestamp convert(Date date) {
        Date utilDate = new java.util.Date();
        Timestamp sqlTimeStamp = convert(utilDate);
        return sqlTimeStamp;
    }

    private String title;
    private String image;
    private String youtube;
    private String startDate;
    private String endDate;
    private String content;
    private String distribution;
    private String teamName;
    private String teamMember;
    private String github;

    @Builder
    public ProjectResponse(String title, String image, String youtube,
                          String content, String teamMember, String teamName,
                          String github, String startDate, String endDate, String distribution
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

    }

    public void addAttribute(String delete, ProjectResponse projectResponse) {
    }
}