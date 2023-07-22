package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.entity.Category;
import com.projet.clubpage.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/category")
    public CommonResponse<Object> getCategoryList() {
        List<Category> categoryList = categoryService.categoryListFindAll();
        return ApiUtils.success(true, 200, "카테고리 조회를 성공했습니다.", categoryList);
    }

}
