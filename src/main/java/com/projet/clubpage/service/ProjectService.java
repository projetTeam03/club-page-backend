package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.ProjectRequest;
import com.projet.clubpage.dto.response.ProjectResponse;
import com.projet.clubpage.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.projet.clubpage.repository.ProjectRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectService {

        private ProjectRepository projectRepository;


    // Entity -> Dto로 변환
//    private ProjectResponse convertEntityToDto(ProjectResponse projectResponse) {
//        return ProjectResponse.builder()
////                    .idx(project.getIdx())
//                    .title(project.getTitle())
//                    .image(project.getImage())
//                    .youtube(project.getYoutube())
//                    .content(project.getContent())
//                    .teamMember(project.getTeamMember())
//                    .teamName(project.getTeamName())
//                    .github(project.getGithub())
//                    .startDate(project.getStartDate())
//                    .endDate(project.getEndDate())
//                    .distribution(project.getDistribution())
//                    .createdDate(project.getCreatedDate())
//                    .mainCategoryIdx(project.getMainCategoryIdx())
//                    .subCategoryIdx(project.getSubCategoryIdx())
//                    .userIdx(project.getUserIdx())
//                    .views(project.getViews())
//                    .likes(project.getLikes())
//                    .deleteYn(project.getDeleteYn())
//                    .modifiedDate(project.getModifiedDate())
//                    .build();
//    }

    @Transactional
    public List<ProjectRequest> getproject() {
        List<Project> projectEntityList = projectRepository.findAll();
        List<ProjectRequest> projectDtoList = new ArrayList<>();

        for(Project project : projectEntityList) {
            ProjectRequest projectDto = ProjectRequest.builder()
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
                    .userIdx(project.getUserIdx())
                    .build();
            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }

    @Transactional
    public ProjectResponse getPost(Integer project_idx) {
        // Optional : NPE(NullPointerException) 방지
        Optional<Project> projectWrapper = projectRepository.findById(project_idx);
        Project project = projectWrapper.get();

        ProjectResponse projectResponse = ProjectResponse.builder()
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
                .userIdx(project.getUserIdx())
                .build();

        return projectResponse;
    }

    @Transactional
    public Integer Post(ProjectRequest projectRequest) {
        return projectRepository.save(projectRequest.toEntity()).getIdx();
    }

    @Transactional
    public void delete(Integer project_idx) {
        projectRepository.deleteById(project_idx);
    }



    public <projectEntity> List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Long join(Project project) {
        return null;
    }

    public void postProject(ProjectRequest projectRequest) {
    }

    public void update(ProjectRequest projectRequest) {
    }
}
