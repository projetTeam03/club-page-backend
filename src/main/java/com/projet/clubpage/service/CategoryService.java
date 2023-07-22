package com.projet.clubpage.service;

import com.projet.clubpage.entity.Category;
import com.projet.clubpage.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> categoryListFindAll() {
        return categoryRepository.findAll();
    }



}
