package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.RecruitModify;
import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.dto.response.RecruitDetail;
import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.entity.Recruit;
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

    /* todo optional 예외처리
    *  todo endDate: null X , duration 설정완료와 함께 endDate 찍혀야.
    *  todo startDate: 1. 등록 시 저장되고 프론트에 안보임 2. 조회 시 프론트에 안보임 3. 상세조회 시 보임. 4. 수정할 때 안보이지만 수정등록후 저장됨.
    *  todo position, tag 갯수 제한 처리
    *  todo views 조회수 중복 방지
    *  todo 상세조회나 수정을 위한 등록페이지 조회시, 레포에서 deleteYn 고려해야 하는지 */



    /* 모집등록 - participants, progress, duration, tag(skill), endDate, position, github, title, contents */
    @ApiOperation(value = "모집등록", notes = "모집공고 게시글 등록")
    @PostMapping("/")
    public CommonResponse<Object> post(@RequestBody RecruitRequest recruitRequest) throws ParseException {
        recruitService.postRecruit(recruitRequest);
          //서비스에 디티오를 인자로.
        return ApiUtils.success(true,200,"공고 등록 성공",null);
    }



    /* 모집공고 리스트 조회 - scraps, end_date, title, position, tag, [user_nickname], views, state, (deleteYn(쿼리)), [comments] */

    /* [남은 일들] 1 tag:
     * 2  */
    @ApiOperation(value = "모집공고 리스트", notes = "모집공고 리스트 조회")
    @GetMapping("/list")
    public CommonResponse<Object> response() throws ParseException {
        List<RecruitResponse> recruitResponseList = recruitService.findAll();

        return ApiUtils.success(true, 200, "성공했습니다.", recruitResponseList);

    }



    /* 특정 모집공고 상세조회 - title, [user_nickname], create_date, progress_method,
    participants, duration, position, tag, contents, views, scraps, [comments]
     */
    @ApiOperation(value = "특정 모집공고", notes = "특정 모집공고 상세조회")
    @GetMapping("/{recruit_idx}")
    public CommonResponse<Object> detail(@PathVariable("recruit_idx") Integer idx) throws ParseException{

//        Recruit recruit = new Recruit();
//        idx = recruit.getIdx();

        recruitService.updateViews(idx); //조회수, idx = recruit_idx


        RecruitDetail recruitDetail = recruitService.findById(idx); //idx = recruit_idx

        return ApiUtils.success(true, 200, "성공했습니다.", recruitDetail);

    }

      /* 특정 모집공고 수정 1 (불러오기) - participants, progress, duration, tag(skill), endDate, position, github, title, contents */

      /* 1 훌라 참고했을 때, 등록 페이지("/api/recruit")에서 수정해야 할 것 같아 보인다
      *  2 등록할 때 작성했던 요소들 다 불러온다 (대신, endDate는 수정불가로) */
     @ApiOperation(value = "특정 모집공고 수정 1", notes = "특정 모집공고 수정(불러오기)")
     @GetMapping("/")
     public CommonResponse<Object> update() throws  Exception {
         Recruit recruit = new Recruit();
         Integer idx = recruit.getIdx();

         RecruitModify recruitModify = recruitService.findRegisterById(idx);

         return ApiUtils.success(true, 200, "성공했습니다.", recruitModify);









     }






}








