package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.ProjectRequest;
import com.projet.clubpage.entity.Project;
import org.springframework.stereotype.Service;
import com.projet.clubpage.repository.ProjectRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Integer savePost(ProjectRequest projectDto) {
        return projectRepository.save(projectDto.toEntity()).getIdx();
    }

    @Transactional
    public List<ProjectRequest> getproject() {
        List<Project> projectEntityList = projectRepository.findAll();
        List<ProjectRequest> projectDtoList = new ArrayList<>();

        for(Project project : projectEntityList) {
            ProjectRequest projectDto = ProjectRequest.builder()
                    .id(project.getIdx())
                    .title(project.getTitle())
                    .image(project.getImage())
                    .youtube(project.getYoutube())
                    .content(project.getContent())
                    .teamMember(project.getTeamMember())
                    .teamName(project.getTeamName())
                    .github(project.getGithub())
                    .startDate(project.getStartDate())
                    .endDate(project.getEndDate())
                    .distribution(project.getDistribution())
                    .createdDate(project.getCreatedDate())
                    .mainCategoryIdx(project.getMainCategoryIdx())
                    .subCategoryIdx(project.getSubCategoryIdx())
                    .userIdx(project.getUserIdx())
                    .views(project.getViews())
                    .likes(project.getLikes())
                    .deleteYn(project.getDeleteYn())
                    .modifiedDate(project.getModifiedDate())
                    .build();
            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }

    @Transactional
    public ProjectRequest getPost(Long id) {
        Project project = projectRepository.findById(id).get();

        ProjectRequest projectDto = ProjectRequest.builder()
                .id(project.getIdx())
                .title(project.getTitle())
                .image(project.getImage())
                .youtube(project.getYoutube())
                .content(project.getContent())
                .teamMember(project.getTeamMember())
                .teamName(project.getTeamName())
                .github(project.getGithub())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .distribution(project.getDistribution())
                .createdDate(project.getCreatedDate())
                .mainCategoryIdx(project.getMainCategoryIdx())
                .subCategoryIdx(project.getSubCategoryIdx())
                .userIdx(project.getUserIdx())
                .views(project.getViews())
                .likes(project.getLikes())
                .deleteYn(project.getDeleteYn())
                .modifiedDate(project.getModifiedDate())
                .build();
        return projectDto;
    }
    @Transactional
    public void deletePost(Long id) {
        projectRepository.deleteById(id);
    }

    public <projectEntity> List<Project> findAll() {
        return projectRepository.findAll();
    }

}
