package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.service.MyPageService;
import com.projet.clubpage.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    private final UserService userService;

    @GetMapping("/api/user/recruit")
    @ApiOperation(value = "작성한 공고", notes = "내가 작성한 공고 리스트 조회")
    public CommonResponse<Object> myRecruitList(@RequestHeader("Authorization") String accessToken) throws ParseException {
        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }

        List<RecruitResponse> recruitResponses = myPageService.myRecruitList(userId);
        return ApiUtils.success(true, 200, "내가 작성한 공고 조회 성공",recruitResponses);
    }

    @GetMapping("/api/user/recruit/scrap")
    @ApiOperation(value = "스크랩한 공고", notes = "내가 스크랩한 공고 리스트 조회")
    public CommonResponse<Object> myScrapRecruitList(@RequestHeader("Authorization") String accessToken) throws ParseException {
        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }

        List<RecruitResponse> recruitResponses = myPageService.myScrapRecruitList(userId);
        return ApiUtils.success(true, 200, "내가 스크랩한 공고 조회 성공",recruitResponses);
    }
}