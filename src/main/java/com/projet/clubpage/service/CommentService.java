package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.RecruitCommentRequest;
import com.projet.clubpage.entity.RecruitComment;
import com.projet.clubpage.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void postRecruitComment(RecruitCommentRequest recruitCommentRequest) {
        RecruitComment recruitComment
    }

}
