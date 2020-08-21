package com.ssafy.devlog.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.ProjectRole;
import com.ssafy.devlog.service.JwtService;
import com.ssafy.devlog.service.ProjectRoleService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/projectrole")
public class ProjectRoleController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectRoleController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ProjectRoleService projectRoleService;
	
	@ApiOperation(value = "프로젝트의 모든 역할내용 리스트를 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_project}")
	public ResponseEntity<List<ProjectRole>> selectAllProjectRole(@PathVariable int seq_post_project) throws Exception {
		logger.debug("selectAllProjectRole - 호출");
		return new ResponseEntity<List<ProjectRole>>(projectRoleService.selectAllProjectRole(seq_post_project), HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로젝트의 역할을 등록하거나 수정한다. (ex. { seq_post_project:348 , role:['abc in frontend','def in backend']} )", response = String.class)
	@PostMapping
	public ResponseEntity<String> updateProjectRole(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updateProjectRole - 호출");
		List<String> role = (List<String>)params.get("role");
		int seq_post_project = (int)params.get("seq_post_project");
		projectRoleService.deleteProjectRole(seq_post_project);
		if(role==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(projectRoleService.insertProjectRole(seq_post_project,role)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
	
	
	
}
