package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {

    List<Recruit> findAllByDeleteYnEquals(String yn); //String yn에서 yn은 임의지정, @query가 위에 있는 경우에는 findAllByDeleteYnEquals 임의 지정 가능. 그런데 없으면 그 자체로 효력.

    @Modifying
    @Query(value = "update Recruit b set b.views=b.views+1 where b.idx=:idx")
    void updateViews(@Param("idx") Integer idx);


}
