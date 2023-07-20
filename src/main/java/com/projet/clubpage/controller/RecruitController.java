package com.projet.clubpage.controller;

import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.RecruitDTO;
import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.service.RecruitService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruit")
public class RecruitController<RecruitJSONRequest> {

    private final RecruitService recruitService;

//    @PostMapping("/")
//    public CommonResponse<Object> postRecruit(
//            @RequestParam("idx") Integer idx,
//            @RequestParam("title") String title,
//            @RequestParam("contents") String contents,
//            @RequestParam("progress_method") String progress_method,
//            @RequestParam("partcipants") Integer partcipants,
//            @RequestParam("views") int views,
//            @RequestParam("user_idx") int user_idx
//
//    ) {
//        RecruitDTO recruitDTO = new RecruitDTO();
//        recruitDTO.setIdx(idx);
//        recruitDTO.setTitle(title);
//        recruitDTO.setContents(contents);
//        recruitDTO.setProgress_method(progress_method);
//        recruitDTO.setPartcipants(partcipants);
//        recruitDTO.setViews(views);
//        recruitDTO.setUser_idx(user_idx);
//        recruitService.saveRecruit(recruitDTO);
//
//
//        return null;
//    }
//
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    @PostMapping("/board")
    public CommonResponse<Object> post(@RequestBody Recruit recruit) {

        recruitService.postRecruit(recruit); //dto 를 서비스단으로 가져감
        return null;



    }



}




