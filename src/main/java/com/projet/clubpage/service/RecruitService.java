package com.projet.clubpage.service;

import com.projet.clubpage.dto.RecruitDTO;
import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    @Transactional //insert, delete, update
    public void postRecruit(Recruit recruit){
       recruit.setViews(0);
       recruit.setScraps(0);
       recruit.setDeleteYn("N");

        recruitRepository.save(recruit); //dto에서 엔티티로 변환된 것을 레포에 저장


    }



}
