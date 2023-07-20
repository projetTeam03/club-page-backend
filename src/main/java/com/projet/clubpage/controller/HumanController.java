package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.entity.Human;
import com.projet.clubpage.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HumanController {

    private final HumanService humanService;

    @PostMapping("/api/human")  //서비스의 메소드 호출.
    public CommonResponse<Object> postHuman(@RequestBody String name){
        humanService.saveHuman(name); //name = @RequestBody String name = Database name
        return null;
    }

    @GetMapping("/api/human")
    public CommonResponse<Object> getHumanList(){
        List<Human> humans = humanService.getHumanList();
        return ApiUtils.success(true, 200, "성공했습니다.", humans);
    }


    



}
