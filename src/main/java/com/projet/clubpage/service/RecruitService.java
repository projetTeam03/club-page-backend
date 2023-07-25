package com.projet.clubpage.service;

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

        //리쿠르트 = recruitRequest(디티오)를 엔티티로 만든거
        Recruit recruit = recruitRequest.toEntity();
        Recruit insertRecruit = recruitRepository.save(recruit); //기본키 값이 안들어 있으면 새로 생성, idx(키) 있는 경우는 수정.

        List<RecruitPosition> recruitPositionsList = positionInsert(recruitRequest.getPosition(), insertRecruit);
        List<RecruitTag> recruitTagsList = tagInsert(recruitRequest.getSkill(), insertRecruit);

        //리쿠르트포지션 레포.저장(리쿠르트 포지션들)
        recruitPositionRepository.saveAll(recruitPositionsList);
        //리쿠르트태그 레포.저장(리쿠르트 태그들)
        recruitTagRepository.saveAll(recruitTagsList);


        System.out.println("");


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

    /* 조회수 */
    @Transactional
    public void updateViews(Integer idx) {
        recruitRepository.updateViews(idx);
    }

    /* 특정 모집공고 상세조회 */
    //idx = recruit_idx

    public RecruitDetail findById(Integer idx) {
        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {

            Recruit recruit = optionalRecruit.get();

            List<Tag> findTagList = recruitTagRepository.getTagByRecruitId(recruit.getIdx());
            List<Position> findPositionList = recruitPositionRepository.getPositionByRecruitId(recruit.getIdx());

            return recruit.toDetailDto(recruit, findPositionList, findTagList);

        } else {
            return null;
        }
    }


    /* 특정 모집공고 수정 */
    @Transactional
    public void update(Integer idx, RecruitRequest recruitRequest) throws ParseException {

        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {

            Recruit recruit1 = optionalRecruit.get();

            //기존에 들어가 있던 태그나 포지션이 삭제가 되어야함.


            //findTagList, findPositionList...
            Recruit recruit = recruitRequest.toEntity();
            recruit.setIdx(idx);
            recruit.setViews(recruit1.getViews());

            recruitRepository.save(recruit);
            recruitTagRepository.deleteByRecruitId(recruit.getIdx());
            List<RecruitTag> recruitTagsList = tagInsert(recruitRequest.getSkill(), recruit);

            recruitPositionRepository.deleteByRecruitId(recruit.getIdx());
            List<RecruitPosition> recruitPositionList = positionInsert(recruitRequest.getPosition(), recruit);


            recruitTagRepository.saveAll(recruitTagsList);
            recruitPositionRepository.saveAll(recruitPositionList);

        } else {

        }

    }


    public List<RecruitTag> tagInsert(List<Integer> tagList, Recruit insertRecruit) {
        List<RecruitTag> recruitTags = new ArrayList<>();
        for (Integer tag : tagList) {
            //디티오.getSkill()에 태그 하나씩 대응
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
        return recruitTags;
    }

    public List<RecruitPosition> positionInsert(List<Integer> positionList, Recruit insertRecruit) {

        List<RecruitPosition> recruitPositions = new ArrayList<>();

        for (Integer position : positionList) {
            //포지션을 디티오의 포지션들(백,프론트,디자이너) 하나씩 대응.

            //리쿠르트 포지션 엔티티의 값을 넣어주는 거(아래 4개)
            RecruitPosition recruitPosition = new RecruitPosition();
            RecruitPositionId recruitPositionId = new RecruitPositionId();
            recruitPosition.setRecruit(insertRecruit);
            recruitPositionId.setRecruitId(insertRecruit.getIdx());

            //레포에서 조회
            Optional<Position> position1 = positionRepository.findById(position);

            if (position1.isPresent()) {


                recruitPositionId.setPositionId(position);
                recruitPosition.setPosition(position1.get());

                recruitPosition.setRecruitPositionId(recruitPositionId);

                recruitPositions.add(recruitPosition);
            }//todo 조건부 캐치 필요
        }
        return recruitPositions;
    }


    /* 특정 모집공고 삭제 */
    public void delete(Integer idx) {

//        Optional<Recruit> recruit = Optional.ofNullable(recruitRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Id의 게시글 입니다.")));
//
//        recruitRepository.deleteById(idx);


        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {

            Recruit recruit = optionalRecruit.get();

//            Recruit recruit1 = recruitRepository.findRecruitByDeleteYnEquals("N");


            recruit.setDeleteYn("Y");

            recruitRepository.save(recruit);


        } else {

        }


    }




}









