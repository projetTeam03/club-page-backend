package com.projet.clubpage.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projet.clubpage.entity.Project;
import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// controller와 service 간에 주고받을 객체를 정의하며, 최종적으로는 view에 뿌려줄 객체
// Entity와 속성이 같을 수 있으나, 여러 service를 거쳐야 하는 경우 dto의 몸집은 더 커짐
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectResponse {

    private String title;
    private String image;
    private String youtube;
    private String startDate;
    private String endDate;
    private String contents;
    private String distribution;
    private String teamName;
    private String teamMember;
    private String github;
    private Integer userIdx;

    @Builder
    public ProjectResponse(String title, String image, String youtube,
                          String contents, String teamMember, String teamName,
                          String github, String startDate, String endDate, String distribution,
                           Integer userIdx
    ) {
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

    }

}