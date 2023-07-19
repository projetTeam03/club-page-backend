package com.projet.clubpage.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// DB 테이블과 매핑되는 객체(Entity)를 정의
// JPA에서는 Entity를 통해 데이터를 조작함
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 파라미터가 없는 기본 생성자를 추가하는 어노테이션 (JPA 사용을 위해 기본 생성자 생성은 필수)
// access는 생성자의 접근 권한을 설정하는 속성이며,  최종적으로 protected BoardEntity() {}와 동일
@Getter
//모든 필드에 getter를 자동생성해주는 어노테이션
@Entity
// 객체를 테이블과 매핑할 엔티티라고 JPA에게 알려주는 역할을 하는 어노테이션
// @Entity가 붙은 클래스는 JPA가 관리
@Table(name = "board")
// 엔티티 클래스와 매핑되는 테이블 정보를 명시하는 어노테이션
// name 속성으로 테이블명을 작성할 수 있으며, 생략 시 엔티티 이름이 테이블명으로 자동 매핑
public class BoardEntity extends TimeEntity {

    @Id
    // 테이블의 기본 키임을 명시하는 어노테이션
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    // 기본키로 대체키를 사용할 때, 기본키 값 생성 전략을 명시
    private Long id;

    @Column(length = 10, nullable = false)
    // 컬럼을 매핑하는 어노테이션
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    // 빌더패턴 클래스를 생성해주는 어노테이션
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}