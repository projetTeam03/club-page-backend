package com.projet.clubpage.service;

import com.projet.clubpage.entity.PositionTag;
import com.projet.clubpage.entity.Tag;
import com.projet.clubpage.repository.PositionTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionTagService {

    private final PositionTagRepository positionTagRepository;

    @Transactional
    public List<Tag> findTagByPositionName(String positionName) {
        return positionTagRepository.findPositionTags(positionName);
    }

}
