package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
//    @Modifying
//    @Query(value = "update Recruit b set b.views=b.views+1 where b.idx=:idx")
//    void updateViews(@Param("idx") Integer idx);
}
