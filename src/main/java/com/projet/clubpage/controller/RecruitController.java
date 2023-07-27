package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.dto.response.RecruitDetail;
import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.service.RecruitService;
import com.projet.clubpage.service.UserService;
import io.swagger.annotations.Api;
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
    private final UserService userService;

    /* todo optional 예외처리 (후순위)

     *  todo 상세조회나 수정을 위한 등록페이지 조회시, 레포에서 deleteYn 고려해야 하는지
     *  todo paging 처리 */


    /* 모집등록 - participants, progress, duration, tag(skill), endDate, position, github, title, contents */
    @ApiOperation(value = "모집등록", notes = "모집공고 게시글 등록")
    @PostMapping("/")
    public CommonResponse<Object> post(@RequestBody RecruitRequest recruitRequest, @RequestHeader("Authorization") String accessToken) throws ParseException {

        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }

        recruitService.postRecruit(recruitRequest, userId);
        //서비스에 디티오를 인자로.
        return ApiUtils.success(true, 200, "공고 등록 성공", null);
    }



    /* 모집공고 리스트 조회 - scraps, end_date, title, position, tag, [user_nickname], views, state, (deleteYn(쿼리)) */

    @ApiOperation(value = "모집공고 리스트", notes = "모집공고 리스트 조회")
    @GetMapping("/list")
    public CommonResponse<Object> response(@RequestParam(required = false) List<Integer> skills, @RequestParam(required = false) String sortType, @RequestParam(required = true) Integer state) throws ParseException {

        List<RecruitResponse> recruitResponseList = recruitService.findAll(skills, sortType, state);

        return ApiUtils.success(true, 200, "성공했습니다.", recruitResponseList);

    }


    /* 특정 모집공고 상세조회 - title, [user_nickname], create_date, progress_method,
    participants, duration, position, tag, contents, views, scraps
     */
    @ApiOperation(value = "특정 모집공고", notes = "특정 모집공고 상세조회")
    @GetMapping("/{recruit_idx}")
    public CommonResponse<Object> detail(@PathVariable("recruit_idx") Integer idx) throws ParseException {

        recruitService.updateViews(idx); //조회수, idx = recruit_idx


        RecruitResponse recruitResponse = recruitService.findById(idx); //idx = recruit_idx

        return ApiUtils.success(true, 200, "성공했습니다.", recruitResponse);

    }



    /* 특정 모집공고 수정 - participants, progress_method, duration, tag(skill), endDate, position, github, title, contents */

    /*  endDate는 수정불가 */

    @ApiOperation(value = "특정 모집공고 수정 ", notes = "특정 모집공고 수정하기")
    @PatchMapping("/{recruit_idx}")
    public CommonResponse<Object> update(@PathVariable("recruit_idx") Integer idx, @RequestBody RecruitRequest recruitRequest, @RequestHeader("Authorization") String accessToken) throws ParseException { //원본 디티오

        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }
        Integer status =  recruitService.update(idx, recruitRequest, userId);
        switch (status){
            case 200: return ApiUtils.success(true, 200, "성공했습니다.", null);
            case 401: return ApiUtils.success(false, 401, "작성한 유저와 현재 유저가 일치하지 않습니다.", null);
            case 404: return ApiUtils.success(false, 404, "수정할 글을 찾을 수 없습니다..", null);
            default: return ApiUtils.success(false, 500, "예기치못한 에러가 발생했습니다.", null);
        }

    }


    /* 특정 모집공고 삭제 */
    @ApiOperation(value = "특정 모집공고 삭제 ", notes = "특정 모집공고 삭제하기")
    @DeleteMapping("/{recruit_idx}")
    public CommonResponse<Object> delete(@PathVariable("recruit_idx") Integer idx, @RequestHeader("Authorization") String accessToken){
        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }
        Integer status = recruitService.delete(idx, userId);

        switch (status){
            case 200: return ApiUtils.success(true, 200, "삭제에 성공했습니다.", null);
            case 401: return ApiUtils.success(false, 401, "작성한 유저와 현재 유저가 일치하지 않습니다.", null);
            case 404: return ApiUtils.success(false, 404, "삭제할 글을 찾을 수 없습니다..", null);
            default: return ApiUtils.success(false, 500, "예기치못한 에러가 발생했습니다.", null);
        }
    }

    @ApiOperation(value= "스크랩 버튼", notes = "특정 모집공고 스크랩")
    @GetMapping("/scraps/{recruit_idx}")
    public CommonResponse<Object> scrap(@PathVariable("recruit_idx") Integer idx, @RequestHeader("Authorization") String accessToken) {
        String token = accessToken.replace("Bearer ", "");
        Long userId = userService.getUserIndex(token);
        if (userId == 401) {
            return ApiUtils.success(false, 401, "올바르지않은 토큰입니다.", null);
        } else if(userId == 404) {
            return ApiUtils.success(true, 404, "유저를 찾을 수 없습니다.", null);
        }
        Integer result = recruitService.recruitScrap(userId, idx);
        if (result == 1) {
            return ApiUtils.success(true, 200, "좋아요 취소 성공.", null);
        } else if (result == -1) {
            return ApiUtils.success(true, 200, "좋아요 성공.", null);
        }
        return null;
    }



}















