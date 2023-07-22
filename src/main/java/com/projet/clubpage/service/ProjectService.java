package com.projet.clubpage.service;

import com.projet.clubpage.entity.ProjectEntity;
import org.springframework.stereotype.Service;
import com.projet.clubpage.repository.ProjectRepository;
import com.projet.clubpage.dto.ProjectDto;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private ProjectRepository boardRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Long savePost(ProjectDto projectDto) {
        return projectRepository.save(projectDto.toEntity()).getId();
    }

    @Transactional
    public List<ProjectDto> getprojectList() {
        List<ProjectEntity> projectEntityList = projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(ProjectEntity project : projectEntityList) {
            ProjectDto projectDto = ProjectDto.builder()
                    .id(project.getId())
                    .author(project.getAuthor())
                    .title(project.getTitle())
                    .content(project.getContent())
                    .createdDate(project.getCreatedDate())
                    .build();
            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }

    @Transactional
    public ProjectDto getPost(Long id) {
        ProjectEntity project = projectRepository.findById(id).get();

        ProjectDto projectDto = ProjectDto.builder()
                .id(project.getId())
                .author(project.getAuthor())
                .title(project.getTitle())
                .content(project.getContent())
                .fileId(project.getFileId())
                .createdDate(project.getCreatedDate())
                .build();
        return projectDto;
    }
    @Transactional
    public void deletePost(Long id) {
        projectRepository.deleteById(id);
    }
}