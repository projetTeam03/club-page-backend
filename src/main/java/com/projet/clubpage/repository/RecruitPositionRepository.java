package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Position;
import com.projet.clubpage.entity.RecruitPosition;
import com.projet.clubpage.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RecruitPositionRepository extends JpaRepository<RecruitPosition, Integer> {

    @Query("SELECT position FROM Position as position " +
            "JOIN recruit_position as rt " +
            "ON 1=1 " +
            "AND position.id = rt.RecruitPositionId.positionId " +
            "WHERE 1=1 " +
            "AND rt.RecruitPositionId.recruitId = :recruitId")
    List<Position> getPositionByRecruitId(Integer recruitId);


    @Transactional
    @Modifying
    @Query("DELETE recruit_position WHERE 1=1 AND RecruitPositionId.recruitId = :recruitId")
    void deleteByRecruitId(Integer recruitId);

}
