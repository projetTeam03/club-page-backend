package com.projet.clubpage.repository;

import com.projet.clubpage.entity.PositionTag;
import com.projet.clubpage.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionTagRepository extends JpaRepository<PositionTag, Integer> {

    @Query("SELECT tag FROM position_tag as pt JOIN pt.position as position ON 1=1 AND position.value = :positionValues JOIN pt.tag as tag")
    List<Tag> findPositionTags(@Param("positionValues") String positionValues);

}
