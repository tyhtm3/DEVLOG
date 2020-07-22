package com.ssafy.devlog.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ProjectService projectService;
	
	
	/* show feed project */
	@ApiOperation(value = "피드에서 모든 프로젝트를 반환한다.", response = List.class)
	@GetMapping(value = "/feed/project")
	public ResponseEntity<List<Project>> selectAllproject() throws Exception {
		logger.debug("selectAllproject - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProject(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드에서 이웃 블로그의 모든 프로젝트를 반환한다.", response = List.class)
	@GetMapping(value = "/feed/project/neighbor/{seq_user}")
	public ResponseEntity<List<Project>> selectAllprojectByNeighbor(@PathVariable int seq_user) throws Exception {
		logger.debug("selectAllprojectByNeighbor - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByNeighbor(seq_user), HttpStatus.OK);
	}

	@ApiOperation(value = "피드에서 선택한 태그에 해당하는 모든 프로젝트를 반환한다.", response = List.class)    
	@GetMapping(value = "/feed/project/tag")
	public ResponseEntity<List<Project>> selectAllprojectByTag(@RequestBody List<String> tag) {
		logger.debug("selectAllprojectByTag - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByTag(tag), HttpStatus.OK);
	}

	
	
	/* show blog project */
	@ApiOperation(value = "블로그 메인에서 모든 프로젝트를 반환한다.", response = List.class)    
	@GetMapping(value = "/blog/project/{seq_blog}")
	public ResponseEntity<List<Project>> selectAllprojectByBlog(@PathVariable int seq_blog) {
		logger.debug("selectAllprojectByBlog - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByBlog(seq_blog), HttpStatus.OK);
	}
	
	@ApiOperation(value = "블로그 메인에서 선택한 태그에 해당하는 모든 프로젝트를 반환한다.", response = List.class)    
	@GetMapping(value = "/blog/project/tag")
	public ResponseEntity<List<Project>> selectAllprojectByBlogByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllprojectByBlogByTag - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByBlogByTag((int)params.get("seq_blog"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	
	
	
	/* basic project crud */
	@ApiOperation(value = "글번호에 해당하는 프로젝트를 반환한다", response = String.class)
	@GetMapping(value = "/project/{seq}")
	public ResponseEntity<Project> selectproject(@PathVariable int seq) {
		logger.debug("selectproject - 호출");
		return new ResponseEntity<Project>(projectService.selectProject(seq), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "새로운 프로젝트를 입력한다.", response = String.class)
	@PostMapping(value = "/project")
	public ResponseEntity<String> insertproject(@RequestBody Map<String, Object> params) {
		logger.debug("insertproject - 호출");
		if (projectService.insertProject((int)params.get("seq_blog"),(Project)params.get("project"))==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "프로젝트를 수정한다.", response = String.class)
	@PutMapping(value = "/project")
	public ResponseEntity<String> updateproject(@RequestBody Project project) {
		logger.debug("updateproject - 호출");
		if (projectService.updateProject(project)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트를 삭제한다.", response = String.class)
	@DeleteMapping(value = "/project/{seq}")
	public ResponseEntity<String> deleteproject(@PathVariable int seq) {
		logger.debug("deleteproject - 호출");
		if (projectService.deleteProject(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	

}
