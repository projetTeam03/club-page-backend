package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.dto.response.RecruitDetail;
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

    /* todo optional 예외처리 (후순위)

     *  todo endDate: null X , duration 설정완료와 함께 endDate 찍혀야.
     *  todo startDate: 1. 등록 시 저장되고 프론트에 안보임 2. 조회 시 프론트에 안보임 3. 상세조회 시 보임. 4. 수정할 때 안보이지만 수정등록후 저장됨.
     *  todo position, tag 갯수 제한 처리
     *  todo 상세조회나 수정을 위한 등록페이지 조회시, 레포에서 deleteYn 고려해야 하는지
     *  todo scraps(유저 번호, 게시글 번호)
     *  todo paging 처리 */


    /* 모집등록 - participants, progress, duration, tag(skill), endDate, position, github, title, contents */
    @ApiOperation(value = "모집등록", notes = "모집공고 게시글 등록")
    @PostMapping("/")
    public CommonResponse<Object> post(@RequestBody RecruitRequest recruitRequest) throws ParseException {
        recruitService.postRecruit(recruitRequest);
        //서비스에 디티오를 인자로.
        return ApiUtils.success(true, 200, "공고 등록 성공", null);
    }



    /* 모집공고 리스트 조회 - scraps, end_date, title, position, tag, [user_nickname], views, state, (deleteYn(쿼리)), [comments] */

    @ApiOperation(value = "모집공고 리스트", notes = "모집공고 리스트 조회")
    @GetMapping("/list")
    public CommonResponse<Object> response() throws ParseException {
        List<RecruitResponse> recruitResponseList = recruitService.findAll();

        return ApiUtils.success(true, 200, "성공했습니다.", recruitResponseList);

    }


    /* 특정 모집공고 상세조회 - title, [user_nickname], create_date, progress_method,
    participants, duration, position, tag, contents, views, scraps
     */
    @ApiOperation(value = "특정 모집공고", notes = "특정 모집공고 상세조회")
    @GetMapping("/{recruit_idx}")
    public CommonResponse<Object> detail(@PathVariable("recruit_idx") Integer idx) throws ParseException {

        recruitService.updateViews(idx); //조회수, idx = recruit_idx


        RecruitDetail recruitDetail = recruitService.findById(idx); //idx = recruit_idx

        return ApiUtils.success(true, 200, "성공했습니다.", recruitDetail);

    }


    /* 특정 모집공고 수정 - participants, progress_method, duration, tag(skill), endDate, position, github, title, contents */

    /*  endDate는 수정불가 */

    @ApiOperation(value = "특정 모집공고 수정 ", notes = "특정 모집공고 수정하기")
    @PatchMapping("/{recruit_idx}")
    public CommonResponse<Object> update(@PathVariable("recruit_idx") Integer idx, @RequestBody RecruitRequest recruitRequest) throws ParseException { //원본 디티오

        recruitService.update(idx, recruitRequest);

        return ApiUtils.success(true, 200, "성공했습니다.", null);

    }

//    @ApiOperation(value = "특정 모집공고 수정 ", notes = "특정 모집공고 수정하기")
//    @PatchMapping("/{recruit_idx}")
//    public CommonResponse<Object> update(@PathVariable("recruit_idx") Integer idx, @RequestBody RecruitRequest recruitRequest) throws ParseException { //원본 디티오
//
//        RecruitRequest updatedRequest = RecruitService.updateRequest(idx, recruitRequest);
//
//        return ApiUtils.success(true, 200, "성공했습니다.", updatedRequest);
//
//    }


}















