package com.projet.clubpage.dto.request;

import com.projet.clubpage.entity.RecruitComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitCommentRequest {

    private String contents;


    @Builder
    public RecruitCommentRequest(String contents) {
        this.contents = contents;
    }

    public RecruitComment toEntity(String contents) {

        return RecruitComment.builder()
                .contents(contents)
                .build();
    }

}


