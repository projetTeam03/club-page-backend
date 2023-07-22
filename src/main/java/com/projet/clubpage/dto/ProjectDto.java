package com.projet.clubpage.dto;

import com.projet.clubpage.entity.ProjectEntity;
import lombok.*;

import java.time.LocalDateTime;

// controller와 service 간에 주고받을 객체를 정의하며, 최종적으로는 view에 뿌려줄 객체
// Entity와 속성이 같을 수 있으나, 여러 service를 거쳐야 하는 경우 dto의 몸집은 더 커짐
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ProjectEntity toEntity() {
        ProjectEntity build = ProjectEntity.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .fileId(fileId)
                .build();
        return build;
    }

    @Builder
    public ProjectDto(Long id, String author, String title, String content, Long fileId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}