package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.entity.*;
import com.projet.clubpage.entity.embeddedId.RecruitPositionId;
import com.projet.clubpage.entity.embeddedId.RecruitTagId;
import com.projet.clubpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitPositionRepository recruitPositionRepository;
    private final RecruitTagRepository recruitTagRepository;
    private final PositionRepository positionRepository;
    private final TagRepository tagRepository;

    //모집등록
    @Transactional //insert, delete, update
    public void postRecruit(RecruitRequest recruitRequest) throws ParseException {

        List<RecruitPosition> recruitPositions = new ArrayList<>();
        List<RecruitTag> recruitTags = new ArrayList<>();

        Recruit recruit = recruitRequest.toEntity();
        Recruit insertRecruit = recruitRepository.save(recruit);

        for (Integer position: recruitRequest.getPosition()) {

            RecruitPosition recruitPosition = new RecruitPosition();

            RecruitPositionId recruitPositionId = new RecruitPositionId();
            recruitPositionId.setRecruitId(insertRecruit.getIdx());

            recruitPosition.setRecruit(insertRecruit);
            Optional<Position> position1 = positionRepository.findById(position);
            if (position1.isPresent()) {
                recruitPositionId.setPositionId(position);
                recruitPosition.setPosition(position1.get());
                recruitPosition.setRecruitPositionId(recruitPositionId);
                recruitPositions.add(recruitPosition);
            }//todo 조건부 캐치 필요
        }

        for (Integer tag: recruitRequest.getSkill()) {

            RecruitTag recruitTag = new RecruitTag();

            RecruitTagId recruitTagId = new RecruitTagId();
            recruitTagId.setRecruitId(insertRecruit.getIdx());

            recruitTag.setRecruit(insertRecruit);
            Optional<Tag> tag1 = tagRepository.findById(tag);
            if (tag1.isPresent()) {
                recruitTagId.setTagId(tag);
                recruitTag.setTag(tag1.get());
                recruitTag.setRecruitTagId(recruitTagId);
                recruitTags.add(recruitTag);
            }//todo 조건부 캐치 필요
        }
        recruitPositionRepository.saveAll(recruitPositions);
        recruitTagRepository.saveAll(recruitTags);

        //dto에서 엔티티로 변환된 것을 레포에 저장
    }

    //모집공고 리스트 조회
//    public List<RecruitRequest> getRecruitList() {
//        List<Recruit> recruitList = recruitRepository.findAll(); //일단 엔티티 타입의 리스트를 레포에서 가져온다
//
//        List<RecruitRequest> recruitRequestList = new ArrayList<>(); //디티오 타입의 리스트는 인스턴스화
//
//        for (Recruit recruit : recruitList) {
//            recruitRequestList.add(RecruitRequest.toRecruitDTO(recruit));
//        }
//        return recruitRequestList;
//    }

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
