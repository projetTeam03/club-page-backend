package com.projet.clubpage.service;

import com.projet.clubpage.dto.request.ProjectRequest;
import com.projet.clubpage.dto.response.ProjectResponse;
import com.projet.clubpage.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.projet.clubpage.repository.ProjectRepository;


import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.projet.clubpage.dto.request.ProjectRequest.convertToTimestamp;

@AllArgsConstructor
@Service
public class ProjectService {

        private ProjectRepository projectRepository;


//     Entity -> Dto로 변환
    private ProjectResponse convertEntityToDto(ProjectResponse projectResponse) {
        return ProjectResponse.builder()
                    .title(projectResponse.getTitle())
                    .image(projectResponse.getImage())
                    .youtube(projectResponse.getYoutube())
                    .contents(projectResponse.getContents())
                    .teamMember(projectResponse.getTeamMember())
                    .teamName(projectResponse.getTeamName())
                    .github(projectResponse.getGithub())
                    .startDate(projectResponse.getStartDate())
                    .endDate(projectResponse.getEndDate())
                    .distribution(projectResponse.getDistribution())
                    .userIdx(projectResponse.getUserIdx())
                    .build();
    }

    @Transactional
    public List<ProjectRequest> getprojectList() {
        List<Project> projectEntityList = projectRepository.findAll();
        List<ProjectRequest> projectDtoList = new ArrayList<>();

        for(Project project : projectEntityList) {
            ProjectRequest projectDto = ProjectRequest.builder()
                    .title(project.getTitle())
                    .image(project.getImage())
                    .youtube(project.getYoutube())
                    .contents(project.getContents())
                    .teamMember(project.getTeamMember())
                    .teamName(project.getTeamName())
                    .github(project.getGithub())
                    .startDate(project.toString())
                    .endDate(String.valueOf(project.getEndDate()))
                    .distribution(project.getDistribution())
                    .userIdx(project.getUserIdx())
                    .build();
            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }

//    public ProjectResponse addProject(ProjectResponse projectResponse) {
//        /* dto -> entity builder */
//        ProjectResponse projectResponse1 = ProjectResponse.builder(projectResponse).build();
//
//        /* save (insert) */
//        projectResponse = projectRepository.save(projectResponse1);
//
//        return projectResponse;
//    }

    @Transactional
    public void getPost(ProjectRequest projectRequest) throws ParseException {
        // Optional : NPE(NullPointerException) 방지
         Project project = projectRequest.toEntity();
         projectRepository.save(project);
    }



    @Transactional
    public Integer Post(ProjectRequest projectRequest) throws ParseException {
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
