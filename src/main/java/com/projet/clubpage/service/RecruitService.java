package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.RecruitModify;
import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.dto.response.RecruitDetail;
import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.entity.*;
import com.projet.clubpage.entity.embeddedId.RecruitPositionId;
import com.projet.clubpage.entity.embeddedId.RecruitTagId;
import com.projet.clubpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    /* 모집등록 */
    @Transactional //insert, delete, update
    public void postRecruit(RecruitRequest recruitRequest) throws ParseException {
        //포지션 - 리쿠르트 포지션
        //태그 - 리쿠르트 태그
        /* 0 or 1 -> 온라인/오프라인 변환 */

        //리쿠르트포지션과 리쿠르트태그 리스트
        List<RecruitPosition> recruitPositions = new ArrayList<>();
        List<RecruitTag> recruitTags = new ArrayList<>();

        //리쿠르트 = recruitRequest(디티오)를 엔티티로 만든거
        Recruit recruit = recruitRequest.toEntity();
        Recruit insertRecruit = recruitRepository.save(recruit);

        //포지션을 디티오의 포지션들(백,프론트,디자이너) 하나씩 대응.
        // position: 스웨거에 입력한 포지션
        for (Integer position : recruitRequest.getPosition()) {

            //디티오로 변환된 엔티티 idx = recruitPosition의 recruit_idx = recruitPositionId의 recruit_idx
            //리쿠르트 포지션 엔티티의 값을 넣어주는 거(아래 4개)
            RecruitPosition recruitPosition = new RecruitPosition();
            RecruitPositionId recruitPositionId = new RecruitPositionId();
            recruitPosition.setRecruit(insertRecruit);
            recruitPositionId.setRecruitId(insertRecruit.getIdx());

            //레포에서 조회
            Optional<Position> position1 = positionRepository.findById(position);

            if (position1.isPresent()) {

                //Ask //position의 idx = recruitPositionId의 position_idx = recruitPosition의 position_idx
                recruitPositionId.setPositionId(position);
                recruitPosition.setPosition(position1.get());

                //recruitPosition의 RecruitPositionId = recruitPositionId
                recruitPosition.setRecruitPositionId(recruitPositionId);

                recruitPositions.add(recruitPosition);
            }//todo 조건부 캐치 필요
        }
        //디티오.getSkill()에 태그 하나씩 대응
        for (Integer tag : recruitRequest.getSkill()) {

            RecruitTag recruitTag = new RecruitTag();

            RecruitTagId recruitTagId = new RecruitTagId();
            //리쿠르트태그 아이디 = 엔티티 아이디(레포에 저장된, 디티오로부터 변환된 엔티티 idx)
            recruitTagId.setRecruitId(insertRecruit.getIdx());

            //리쿠르트 태그
            recruitTag.setRecruit(insertRecruit);
            Optional<Tag> tag1 = tagRepository.findById(tag);
            if (tag1.isPresent()) {
                recruitTagId.setTagId(tag);
                recruitTag.setTag(tag1.get());
                recruitTag.setRecruitTagId(recruitTagId);
                recruitTags.add(recruitTag);
            }//todo 조건부 캐치 필요
        }
        //리쿠르트포지션 레포.저장(리쿠르트 포지션들)
        recruitPositionRepository.saveAll(recruitPositions);
        //리쿠르트태그 레포.저장(리쿠르트 태그들)
        recruitTagRepository.saveAll(recruitTags);


    }


    /* 모집공고 리스트 조회 */
    public List<RecruitResponse> findAll() throws ParseException {

        //recruitList = 레포에서 DeleteYn="N" 인 것들만 가져온다.
        List<Recruit> recruitList = recruitRepository.findAllByDeleteYnEquals("N");

        List<RecruitResponse> recruitResponseList = new ArrayList<>();


        for (Recruit recruit : recruitList) {

            List<Tag> findTagList = recruitTagRepository.getTagByRecruitId(recruit.getIdx());
            List<Position> findPositionList = recruitPositionRepository.getPositionByRecruitId(recruit.getIdx());

            RecruitResponse recruitResponse = recruit.toDto(recruit, findPositionList, findTagList); //dto로 변환된 recruit,findPositionList,findTagList = recruitResponse

            recruitResponseList.add(recruitResponse);

        }

        return recruitResponseList;
    }


    /* 특정 모집공고 상세조회 */
    //idx = recruit_idx
    public void updateViews(Integer idx) {
        recruitRepository.updateViews(idx);

    }

    public RecruitDetail findById(Integer idx) {
        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {

            Recruit recruit = optionalRecruit.get();

            List<Tag> findTagList = recruitTagRepository.getTagByRecruitId(recruit.getIdx());
            List<Position> findPositionList = recruitPositionRepository.getPositionByRecruitId(recruit.getIdx());

            RecruitDetail recruitDetail = recruit.toDetailDto(recruit, findPositionList, findTagList);
            return recruitDetail;

        } else {
            return null;
        }
    }

    /* 특정 모집공고 수정 1 (불러오기) */
    public RecruitModify findRegisterById(Integer idx) {
        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {
            Recruit recruit = optionalRecruit.get();

            List<Tag> findTagList = recruitTagRepository.getTagByRecruitId(recruit.getIdx());
            List<Position> findPositionList = recruitPositionRepository.getPositionByRecruitId(recruit.getIdx());

            RecruitModify recruitModify = recruit.toRegisterDto(recruit, findPositionList, findTagList);
            return recruitModify;
        } else {
            return null;
        }
    }






}


    /* 특정 모집공고 수정 */