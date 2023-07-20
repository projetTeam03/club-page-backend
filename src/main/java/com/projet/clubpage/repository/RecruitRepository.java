package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
}
