package com.projet.clubpage.dto.request;

import com.projet.clubpage.entity.Position;
import com.projet.clubpage.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateRequestDto {
    private Integer progress;
    private Integer participants;
    private String duration;
    private List<Position> position;
    private List<Tag> skill;
    private String github;
    private String title;
    private String contents;

    @Builder
    public UpdateRequestDto(Integer progress, Integer participants, String duration, String github, String title, String contents,List<Position> listPosition, List<Tag> listTag) {
        this.progress = progress;
        this.participants = participants;
        this.duration = duration;
        this.github = github;
        this.title = title;
        this.contents = contents;
        this.skill = listTag;
        this.position = listPosition;
    }
}
