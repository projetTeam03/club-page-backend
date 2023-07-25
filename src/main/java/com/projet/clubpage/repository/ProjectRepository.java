package com.projet.clubpage.repository;

import com.projet.clubpage.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

// 데이터를 가져오거나 조작하는 함수를 정의
// interface를 implements 하여 미리 만들어진 함수를 사용할 수 있으며, 또한 직접 구현이 가능
// JpaRepository<Entity 클래스, PK 타입>
//        => Entity Class가 Board이며, Primary Key의 Type은 Long
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}