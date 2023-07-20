package com.projet.clubpage.service;

import com.projet.clubpage.entity.Position;
import com.projet.clubpage.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public List<Position> getPositionList() {
        return positionRepository.findAll();
    }

}
