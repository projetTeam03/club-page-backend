package com.projet.clubpage.service;

import com.projet.clubpage.dto.response.RecruitResponse;
import com.projet.clubpage.entity.*;
import com.projet.clubpage.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final RecruitRepository recruitRepository;
    private final RecruitTagRepository recruitTagRepository;
    private final RecruitPositionRepository recruitPositionRepository;
    private final UserRepository userRepository;
    private final ScrapRepository scrapRepository;

    public List<RecruitResponse> myRecruitList(Long userIdx) throws ParseException {
        List<RecruitResponse> recruitResponseList = new ArrayList<>();
        List<Recruit> recruitList = recruitRepository.findAllByUserIdxAndDeleteYnEquals(userIdx, "N");
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

    public List<RecruitResponse> myScrapRecruitList(Long userIdX) throws ParseException {
        List<RecruitResponse> recruitResponseList = new ArrayList<>();
        List<Recruit> recruitList = scrapRepository.findByScrapId_UserId(userIdX);

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

}
