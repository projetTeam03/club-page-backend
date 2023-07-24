package com.projet.clubpage.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileRequest {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public com.projet.clubpage.entity.File toEntity() {
        com.projet.clubpage.entity.File build = com.projet.clubpage.entity.File.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileRequest(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}