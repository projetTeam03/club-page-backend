package com.projet.clubpage.controller;

import com.projet.clubpage.common.ApiUtils;
import com.projet.clubpage.common.CommonResponse;
import com.projet.clubpage.dto.request.ProjectRequest;
import com.projet.clubpage.entity.Project;
import com.projet.clubpage.service.ProjectService;
import com.projet.clubpage.service.FileService;
import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Data
@RequestMapping("/api")
public class ProjectController {
    private ProjectService projectService;
    private FileService fileService;

    public ProjectController(ProjectService projectService, FileService fileService) {
        this.projectService = projectService;
        this.fileService = fileService;
    }

    @PostMapping("/project")
    public CommonResponse<Object> post(@RequestBody ProjectRequest projectRequest) {
        // TODO : requestbody dto 설정
            List<Project> projectEntityList = projectService.findAll();
            return ApiUtils.success(true, 200, "프로젝트 등록", projectEntityList);
    }

    @GetMapping("/project/list")
    public CommonResponse<Object> list(@RequestParam(required = false) String list) {
            List<Project> projectEntityList = projectService.findAll();
            return ApiUtils.success(true, 200, "프로젝트 리스트 조회", projectEntityList);
    }

    @GetMapping("/project/{project_idx}")
    public CommonResponse<Object> detail(@PathVariable("project_idx") Integer idx, Model model) {
        ProjectRequest projectDto = projectService.getPost(Long.valueOf(idx));
        model.addAttribute("detail", projectDto);
        return ApiUtils.success(true, 200, "특정 프로젝트 상세 조회", projectDto);
    }

    @GetMapping("/project/like/{project_idx}")
    public CommonResponse<Object> like(@PathVariable("project_idx") Integer idx, Model model) {
        ProjectRequest projectDto = projectService.getPost(Long.valueOf(idx));
        model.addAttribute("like", projectDto);
        return ApiUtils.success(true, 200, "특정 프로젝트 좋아요", projectDto);
    }

    @PatchMapping("/project/{project_idx}")
    public CommonResponse<Object> update(@PathVariable("project_idx") Integer idx, Model model) {
        ProjectRequest projectDto = projectService.getPost(Long.valueOf(idx));
        model.addAttribute("update", projectDto);
        return ApiUtils.success(true, 200, "특정 프로젝트 수정", projectDto);
    }


    @DeleteMapping("/project/{project_idx}")
    public CommonResponse<Object> delete(@PathVariable("project_idx") Integer idx, Model model) {
        ProjectRequest projectDto = projectService.getPost(Long.valueOf(idx));
        model.addAttribute("delete", projectDto);
        return ApiUtils.success(true, 200, "특정 프로젝트 삭제", projectDto);
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
