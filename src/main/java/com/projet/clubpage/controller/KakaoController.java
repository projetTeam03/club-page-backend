package com.projet.clubpage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    @Description("소셜 로그인 후 실행되는 API, 인가 코드를 통한 사용자 조회, 서비스 회원가입")
    @GetMapping("/api/oauth/kakao")
    @ResponseBody
    public String kakaoOauth(@RequestParam("code") String code) {

        return "true";
    }
}
