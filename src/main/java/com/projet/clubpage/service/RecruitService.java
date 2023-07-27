package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.RecruitRequest;
import com.projet.clubpage.dto.response.RecruitDetail;
import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.entity.*;
import com.projet.clubpage.entity.embeddedId.RecruitPositionId;
import com.projet.clubpage.entity.embeddedId.RecruitTagId;
import com.projet.clubpage.entity.embeddedId.ScrapId;
import com.projet.clubpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final RecruitPositionRepository recruitPositionRepository;
    private final RecruitTagRepository recruitTagRepository;
    private final PositionRepository positionRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;


    /* 모집등록 */
    @Transactional
    public void postRecruit(RecruitRequest recruitRequest, Long userId) throws ParseException {

        Recruit recruit = recruitRequest.toEntity();
        recruit.setUserIdx(userId);

        Recruit insertRecruit = recruitRepository.save(recruit);

        List<RecruitPosition> recruitPositionsList = positionInsert(recruitRequest.getPosition(), insertRecruit);
        List<RecruitTag> recruitTagsList = tagInsert(recruitRequest.getSkill(), insertRecruit);

        //리쿠르트포지션 레포.저장(리쿠르트 포지션들)
        recruitPositionRepository.saveAll(recruitPositionsList);
        //리쿠르트태그 레포.저장(리쿠르트 태그들)
        recruitTagRepository.saveAll(recruitTagsList);

    }


    /* 모집공고 리스트 조회 */
    public List<RecruitResponse> findAll(List<Integer> skills, String sortType, Integer state) throws ParseException {

        String sortColumn = "";

        if (sortType == null){
            sortColumn = "createDate";
        } else if (sortType.equals("scrap")) {
            sortColumn = "scrap";
        } else if (sortType.equals("views")) {
            sortColumn = "views";
        } else {
            sortColumn = "createDate";
        }

        List<Recruit> recruitList = new ArrayList<>();

        //recruitList = 레포에서 DeleteYn="N" 인 것들만 가져온다.
        if (skills == null || skills.isEmpty()) {
            recruitList = recruitRepository.findAllByDeleteYnEquals("N", state, Sort.by(sortColumn).descending());
        } else {
            recruitList = recruitTagRepository.getRecruitTagByList(skills, state, Sort.by(sortColumn).descending());
        }

        List<RecruitResponse> recruitResponseList = new ArrayList<>();

        for (Recruit recruit : recruitList) {

            List<Tag> findTagList = recruitTagRepository.getTagByRecruitId(recruit.getIdx());
            List<Position> findPositionList = recruitPositionRepository.getPositionByRecruitId(recruit.getIdx());
            Optional<User> findUser = userRepository.findById(recruit.getUserIdx());

            Long scrapCount = scrapRepository.countByScrapId_RecruitId(recruit.getIdx());

            RecruitResponse recruitResponse = recruit.toDto(recruit, findPositionList, findTagList, findUser.get(), scrapCount);

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

    public RecruitResponse findById(Integer idx) throws ParseException {
        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {

            Recruit recruit = optionalRecruit.get();

            List<Tag> findTagList = recruitTagRepository.getTagByRecruitId(recruit.getIdx());
            List<Position> findPositionList = recruitPositionRepository.getPositionByRecruitId(recruit.getIdx());
            Optional<User> findUser = userRepository.findById(recruit.getUserIdx());

            Long scrapCount = scrapRepository.countByScrapId_RecruitId(recruit.getIdx());

            return recruit.toDto(recruit, findPositionList, findTagList, findUser.get(), scrapCount);
        } else {
            return null;
        }
    }


    /* 특정 모집공고 수정 */
    @Transactional
    public Integer update(Integer idx, RecruitRequest recruitRequest, Long userId) throws ParseException {

        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (userId == optionalRecruit.get().getUserIdx()) {
            if (optionalRecruit.isPresent()) {

                Recruit recruit1 = optionalRecruit.get();
                //기존에 들어가 있던 태그나 포지션이 삭제가 되어야함.
                //findTagList, findPositionList
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
                return 200;

            } else {
                return 404;
            }
        } else {
            return 401;
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
    public Integer delete(Integer idx, Long userId) {

        Optional<Recruit> optionalRecruit = recruitRepository.findById(idx);

        if (optionalRecruit.isPresent()) {

            Recruit recruit = optionalRecruit.get();
            if (userId == recruit.getUserIdx()) {
                recruitRepository.deleteRecruit(idx);
                return 200;
            } else {
                return 401;
            }
        } else {
            return 404;
        }

    }

    @Transactional
    public Integer recruitScrap(Long userId, Integer recruitId) {

        Optional<Scrap> scrap = scrapRepository.getScrapByRecruitUser(recruitId, userId);
        if (scrap.isPresent()) {
            scrapRepository.deleteScrapByRecruitUser(recruitId, userId);
            return 1;
        } else {
            Scrap newScrap = new Scrap();
            ScrapId scrapId = new ScrapId();
            scrapId.setRecruitId(recruitId);
            scrapId.setUserId(userId);
            newScrap.setScrapId(scrapId);

            Optional<Recruit> recruit = recruitRepository.findById(recruitId);
            newScrap.setRecruit(recruit.get());
            Optional<User> user = userRepository.findById(userId);
            newScrap.setUser(user.get());


            scrapRepository.save(newScrap);
            return -1;
        }
    }


}









