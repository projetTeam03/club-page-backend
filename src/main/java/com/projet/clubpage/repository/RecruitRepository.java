package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Recruit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {

    @Query("SELECT r FROM Recruit r WHERE 1=1 AND r.deleteYn = :yn AND r.state = :state ")
    List<Recruit> findAllByDeleteYnEquals(String yn, Integer state, Sort by); //String yn에서 yn은 임의지정, @query가 위에 있는 경우에는 findAllByDeleteYnEquals 임의 지정 가능. 그런데 없으면 그 자체로 효력.



    @Modifying
    @Query(value = "UPDATE Recruit b SET b.views=b.views+1 WHERE b.idx=:idx")
    void updateViews(@Param("idx") Integer idx);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Recruit b SET b.deleteYn = 'Y' WHERE b.idx=:idx ")
    void deleteRecruit(@Param("idx") Integer idx);




}
