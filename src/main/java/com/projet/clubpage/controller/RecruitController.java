package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.service.RecruitService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruit")
public class RecruitController<RecruitJSONRequest> {

    private final RecruitService recruitService;

    //모집등록(DTO 없이)
    @ApiOperation(value = "모집등록", notes = "모집공고 게시글 등록")
    @PostMapping("/")
    public CommonResponse<Object> post(@RequestBody RecruitRequest recruitRequest) throws ParseException {
           recruitService.postRecruit(recruitRequest);
          //서비스에 디티오를 인자로.
           return null;
    }

    //모집공고 리스트 조회(DTO 추가) - scraps, end_date, title, position, tags, user_nickname, views, state(?)

    @ApiOperation(value = "모집공고 리스트", notes = "모집공고 리스트 조회")
    @GetMapping("/list")
    public CommonResponse<Object> response(RecruitResponse recruitResponse) throws ParseException {
        List<RecruitResponse> recruitResponseList = recruitService.findAll();

        return ApiUtils.success(true, 200, "성공했습니다.", recruitResponseList);

    }


    //특정 모집공고 상세조회 - title, user_idx, create_date, participant, duration, progress_method, views, scraps, tag_name, tag_url...

//    @GetMapping("/{recruit_idx}")
//    public CommonResponse<Object> findById(@PathVariable("recruit_idx") Integer idx){
//        recruitService.updateViews(idx);
//        RecruitDTO recruitDTO = recruitService.findById(idx);
//
//        return ApiUtils.success(true, 200, "성공했습니다.", recruitDTO);
//
//    }




}




