package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.entity.PositionTag;
import com.projet.clubpage.entity.Tag;
import com.projet.clubpage.service.PositionTagService;
import com.projet.clubpage.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final PositionTagService positionTagService;

    @GetMapping("/api/tag")
    public CommonResponse<Object> getTagList(@RequestParam(required = false) String positionName) {

        if (positionName == null || positionName.equals("") || positionName.equals("all")) {
            List<Tag> tagList = tagService.findAll();
            return ApiUtils.success(true, 200, "기술 스택 조회 성공", tagList);
        } else if(positionName.equals("front") || positionName.equals("back") || positionName.equals("design")) {
            List<Tag> positionTags = positionTagService.findTagByPositionName(positionName);
            return ApiUtils.success(true, 200, positionName +" 기술 스택 조회 성공", positionTags);
        } else {
            return ApiUtils.success(false, 404, positionName + "은 없는 포지션입니다.", null);
        }
    }


}
