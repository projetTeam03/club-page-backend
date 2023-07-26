package com.projet.clubpage.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.ProjectRequest;
import com.projet.clubpage.dto.response.ProjectResponse;

import com.projet.clubpage.service.ProjectService;

import lombok.*;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
@Getter
@Setter
@RestController
@Data
@RequestMapping("/api")
public class ProjectController {



    private ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;

    }

    @PostMapping("/project")
    public CommonResponse<Object> post(@RequestBody ProjectRequest projectRequest) throws ParseException {
        projectService.getPost(projectRequest);
        return ApiUtils.success(true, 200, "프로젝트 등록",null);
    }

    @GetMapping("/project/list")
    public CommonResponse<Object> list(ProjectRequest ProjectRequest) {
            List<ProjectRequest> projectRequestList = projectService.getprojectList();
        ProjectRequest.addAttribute("List", projectRequestList);
            return ApiUtils.success(true, 200, "프로젝트 리스트 조회", projectRequestList);
    }

    @GetMapping("/project/{project_idx}")
    public CommonResponse<ProjectResponse> detail(@PathVariable("project_idx") Integer project_idx) throws ParseException {
//        ProjectResponse detail = projectService.getPost(project_idx);
        return ApiUtils.success(true, 200, "특정 프로젝트 상세 조회",null);
    }

    @GetMapping("/project/like/{project_idx}")
    public CommonResponse<Object> like(@PathVariable("project_idx") Integer project_idx, @RequestBody ProjectRequest projectRequest) {
        projectService.postProject(projectRequest);
        return ApiUtils.success(true, 200, "특정 프로젝트 좋아요",projectRequest);
    }

    @PatchMapping("/project/{project_idx}")
    public CommonResponse<Object> update(@PathVariable("project_idx") Integer project_idx, @RequestBody ProjectRequest projectRequest) {
        projectService.update(projectRequest);
        return ApiUtils.success(true, 200, "특정 프로젝트 수정",projectRequest);
    }


    @DeleteMapping("/project/{project_idx}")
    public CommonResponse<Object> delete(@PathVariable("project_idx") Integer project_idx, @RequestBody ProjectRequest projectRequest) {
        projectService.delete(project_idx);
        return ApiUtils.success(true, 200, "특정 프로젝트 삭제", projectRequest);
    }

//    @PostMapping("/post")
//    public String write(@RequestParam("file") MultipartFile files, ProjectDto projectDto) {
//        try {
//            String origFilename = files.getOriginalFilename();
//            String filename = new MD5Generator(origFilename).toString();
//            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            String savePath = System.getProperty("user.dir") + "\\files";
//            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
//            if (!new File(savePath).exists()) {
//                try{
//                    new File(savePath).mkdir();
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }
//            String filePath = savePath + "\\" + filename;
//            files.transferTo(new File(filePath));
//
//            FileDto fileDto = new FileDto();
//            fileDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
//            fileDto.setFilePath(filePath);
//
//            Long fileId = fileService.saveFile(fileDto);
//            projectDto.setFileId(fileId);
//            projectService.savePost(projectDto);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/";
//    }
//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
//        FileDto fileDto = fileService.getFile(fileId);
//        Path path = Paths.get(fileDto.getFilePath());
//        Resource resource = new InputStreamResource(Files.newInputStream(path));
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
//                .body(resource);
//    }
}
