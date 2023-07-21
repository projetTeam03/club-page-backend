package com.projet.clubpage.repository;

import com.projet.clubpage.entity.RecruitTag;
import com.projet.clubpage.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecruitTagRepository extends JpaRepository<RecruitTag, Integer> {


    @Query("SELECT tag FROM Tag as tag JOIN recruit_tag as rt ON 1=1 AND tag.id = rt.recruitTagId.tagId WHERE 1=1 AND rt.recruitTagId.recruitId = :recruitId")
    List<Tag> getTagByRecruitId(Integer recruitId);

}
