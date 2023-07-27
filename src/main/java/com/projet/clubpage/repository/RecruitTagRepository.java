package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Recruit;
import com.projet.clubpage.entity.RecruitTag;
import com.projet.clubpage.entity.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RecruitTagRepository extends JpaRepository<RecruitTag, Integer> {


    @Query("SELECT tag FROM Tag as tag JOIN recruit_tag as rt ON 1=1 AND tag.id = rt.recruitTagId.tagId WHERE 1=1 AND rt.recruitTagId.recruitId = :recruitId")
    List<Tag> getTagByRecruitId(Integer recruitId);

    @Query("SELECT DISTINCT r " +
            "FROM Recruit as r " +
            " JOIN recruit_tag AS rt " +
            "ON 1=1 " +
            "AND r.idx = rt.recruitTagId.recruitId " +
            "AND rt.recruitTagId.tagId IN (:skills) " +
            "WHERE 1=1" +
            "AND r.deleteYn = 'N'" +
            "AND r.state = :state ")
    List<Recruit> getRecruitTagByList(@Param(("skills")) List<Integer> skills, @Param(("state")) Integer state, Sort by);

    @Transactional
    @Modifying
    @Query("DELETE recruit_tag WHERE 1=1 AND recruitTagId.recruitId = :recruitId")
    void deleteByRecruitId(Integer recruitId);

}
