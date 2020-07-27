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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value="/api/project")
public class ProjectController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ProjectService projectService;
	
	/* 	비로그인 (seq_user==0) : 공개범위가 '전체'인 프로젝트 반환
	 * 	   로그인 (seq_user!=0) : 공개범위가 '전체'인 프로젝트와 '이웃공개/비공개'인 프로젝트의 접근여부 판별하여 반환  */
	
	
	/* show feed project */
	@ApiOperation(value = "피드에서 모든 프로젝트 반환.", response = List.class)
	@GetMapping(value = "/feed/{seq_user}")
	public ResponseEntity<List<Project>> selectAllProject(@PathVariable int seq_user) throws Exception {
		logger.debug("selectAllProject - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProject(seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드에서 이웃 블로그의 모든 프로젝트를 반환.", response = List.class)
	@GetMapping(value = "/feed/neighbor/{seq_user}")
	public ResponseEntity<List<Project>> selectAllProjectByNeighbor(@PathVariable int seq_user) throws Exception {
		logger.debug("selectAllProjectByNeighbor - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByNeighbor(seq_user), HttpStatus.OK);
	}

	// Map을 받아오기 때문에 Get 대신 Post를 사용함.
	@ApiOperation(value = "피드에서 선택된 태그를 가진 모든 프로젝트를 반환. (ex. 'seq_user':1 , 'tag': {'java','mysql'} )", response = List.class)    
	@PostMapping(value = "/feed/tag")
	public ResponseEntity<List<Project>> selectAllProjectByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllProjectByTag - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByTag((int)params.get("seq_user"),(List<String>)params.get("tag")), HttpStatus.OK);
	}

	
	
	
	
	/* show blog project */
	@ApiOperation(value = "블로그 메인에서 모든 프로젝트를 반환.", response = List.class)    
	@GetMapping(value = "/blog/{seq_blog}/{seq_user}")
	public ResponseEntity<List<Project>> selectAllProjectByBlog(@PathVariable int seq_blog, @PathVariable int seq_user) {
		logger.debug("selectAllProjectByBlog - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByBlog(seq_user, seq_blog), HttpStatus.OK);
	}
	
	// Map을 받아오기 때문에 Get 대신 Post를 사용함.
	@ApiOperation(value = "블로그 메인에서 선택한 태그에 해당하는 모든 프로젝트를 반환 (ex. 'seq_user':0 , 'seq_blog':1 , 'tag': {'java','mysql'} )", response = List.class)    
	@PostMapping(value = "/blog/tag")
	public ResponseEntity<List<Project>> selectAllProjectByBlogByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllProjectByBlogByTag - 호출");
		return new ResponseEntity<List<Project>>(projectService.selectAllProjectByBlogByTag((int)params.get("seq_user"),(int)params.get("seq_blog"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	

	/* basic project crud */
	@ApiOperation(value = "글번호에 해당하는 프로젝트를 반환", response = String.class)
	@GetMapping(value = "/{seq}")
	public ResponseEntity<Project> selectProject(@PathVariable int seq) {
		logger.debug("selectProject - 호출");
		return new ResponseEntity<Project>(projectService.selectProject(seq), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "새로운  프로젝트 입력 ( seq_blog, title, disclosure, summary, start_date, role ) 필수  ", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertProject(@RequestBody Project project) {
		
		logger.debug("insertProject - 호출");
		
		// insertProject 이후 projectBasic 객체에 seq 받아오기 위한 작업
		Project pjt = new Project();
		pjt = project;
		projectService.insertPost(pjt);
		
		if (projectService.insertPostProject(pjt)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = " 프로젝트 수정", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateProject(@RequestBody Project project) {
		logger.debug("updateProject - 호출");
		if (projectService.updatePost(project)==1&&projectService.updatePostProject(project)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트 삭제", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deleteProject(@PathVariable int seq) {
		logger.debug("deleteProject - 호출");
		if (projectService.deleteProject(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	

}
