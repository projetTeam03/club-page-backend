package com.projet.clubpage.repository;

import com.projet.clubpage.entity.RecruitComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<RecruitComment, Integer> {
}
