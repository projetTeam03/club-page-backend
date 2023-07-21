package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.entity.Position;
import com.projet.clubpage.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping("/api/position")
    public CommonResponse<Object> getPositionList() {
        List<Position> positions = positionService.getPositionList();
        return ApiUtils.success(true, 200, "포지션 조회 성공", positions);
    }


}
