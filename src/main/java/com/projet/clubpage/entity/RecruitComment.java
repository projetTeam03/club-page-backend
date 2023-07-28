package com.projet.clubpage.entity;

import com.projet.clubpage.dto.request.RecruitCommentRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Table(name = "recruit_comment")
@NoArgsConstructor
public class RecruitComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "contents")
    private String contents;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_idx")
    private Recruit recruit;


    @Column(name = "depth")
    private Integer depth;


    @Column(name = "parents_comment_idx")
    private Integer parentsCommentIdx;

    @Builder
    public RecruitComment(String contents, User user, Recruit recruit, Integer depth, Integer parentsCommentIdx) {
        this.contents = contents;
        this.user = user;
        this.recruit = recruit;
        this.depth = depth;
        this.parentsCommentIdx = parentsCommentIdx;
    }

}