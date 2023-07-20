package com.projet.clubpage.service;

import com.projet.clubpage.dto.RecruitDTO;
import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;

    //모집등록
    @Transactional //insert, delete, update
    public void postRecruit(Recruit recruit) {
        recruit.setViews(0);
        recruit.setScraps(0);
        recruit.setDeleteYn("N");

        recruitRepository.save(recruit); //dto에서 엔티티로 변환된 것을 레포에 저장
    }

    //모집공고 리스트 조회
    public List<RecruitDTO> getRecruitList() {
        List<Recruit> recruitList = recruitRepository.findAll(); //일단 엔티티 타입의 리스트를 레포에서 가져온다

        List<RecruitDTO> recruitDTOList = new ArrayList<>(); //디티오 타입의 리스트는 인스턴스화

        for (Recruit recruit : recruitList) {
            recruitDTOList.add(RecruitDTO.toRecruitDTO(recruit));
        }
        return recruitDTOList;
    }

//    public void updateViews(Integer idx) {
//
//        recruitRepository.updateViews(idx);
//
//    }
//
//    public RecruitDTO findById(Integer idx) {
//        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);
//        if (optionalRecruit.isPresent()) {
//            Recruit recruit = optionalRecruit.get();
//            RecruitDTO recruitDTO = RecruitDTO.toRecruitDTO(recruit);
//            return recruitDTO;
//        } else {
//            return null;
//        }
//
//    }
}
