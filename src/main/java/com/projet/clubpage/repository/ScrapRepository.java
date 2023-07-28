package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.entity.Scrap;
import com.projet.clubpage.entity.embeddedId.ScrapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {

    @Query("SELECT s FROM Scrap as s " +
            "WHERE 1=1 " +
            "AND s.scrapId.recruitId = :recruitId " +
            "AND s.scrapId.userId = :userId")
    Optional<Scrap> getScrapByRecruitUser(@Param("recruitId") Integer recruitId, @Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM Scrap AS s" +
            " WHERE 1=1" +
            " AND s.scrapId.recruitId = :recruitId " +
            "AND s.scrapId.userId = :userId")
    void deleteScrapByRecruitUser(@Param("recruitId") Integer recruitId, @Param("userId") Long userId);

    Long countByScrapId_RecruitId(@Param("recruitId") Integer recruitId);

    @Query("SELECT s.recruit FROM Scrap as s WHERE 1=1 AND s.recruit.deleteYn = 'N' AND s.scrapId.userId = :userIdx")
    List<Recruit> findByScrapId_UserId(Long userIdx);
}
