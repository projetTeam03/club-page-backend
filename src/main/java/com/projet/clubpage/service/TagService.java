package com.projet.clubpage.service;

import com.projet.clubpage.entity.Tag;
import com.projet.clubpage.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;


    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

}
