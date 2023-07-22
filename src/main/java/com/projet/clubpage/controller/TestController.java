package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Data
    private class TestStatus {
        private int code;
        private String message;
    }

    @Data
    private class TestOutput {
        private int id;
        private String username;
        private String userProfile;
        private String content;
        private boolean isMine;
    }


    @GetMapping("/api/test")
    public CommonResponse<Object> getTest() {

        TestStatus testStatus = new TestStatus();
        testStatus.setCode(200);
        testStatus.setMessage("Successfully get comments.");

        TestOutput testOutput = new TestOutput();
        testOutput.setId(1);
        testOutput.setUsername("노원호");
        testOutput.setMine(false);
        testOutput.setContent("글내용입니다글내용입니다글내용입니다글내용입니다글내용입니다글내용입니다글내용입니다");
        testOutput.setUserProfile("resource/~~~/~~.jpg");


        return ApiUtils.success(true, 200,  "성공", testOutput);
    }
}
