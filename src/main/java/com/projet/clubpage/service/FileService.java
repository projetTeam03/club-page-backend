package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.FileRequest;
import com.projet.clubpage.repository.FileRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileRequest fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileRequest getFile(Long id) {
        com.projet.clubpage.entity.File file = fileRepository.findById(id).get();

        FileRequest fileDto = FileRequest.builder()
                .id(id)
                .origFilename(file.getOrigFilename())
                .filename(file.getFilename())
                .filePath(String.valueOf(file.getFilePath()))
                .build();
        return fileDto;
    }
}