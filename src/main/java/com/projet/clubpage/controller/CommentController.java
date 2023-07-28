package com.projet.clubpage.controller;


import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.RecruitCommentRequest;
import com.projet.clubpage.service.CommentService;
import com.projet.clubpage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/api/comment/recruit/{recruit_idx}")
    public CommonResponse<Object> postRecruitComment(@PathVariable String recruit_idx, @RequestParam(required = true)RecruitCommentRequest recruitCommentRequest, @RequestHeader("Authorization") String accessToken) {
        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }
        


        return ApiUtils.success(true,200,"댓글 작성에 성공했습니다.",null);
    }
}
