package com.ssafy.devlog.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.PortfolioProject;
import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.dto.ProjectRole;
import com.ssafy.devlog.dto.ProjectStack;
import com.ssafy.devlog.dto.ProjectWithRoleStack;
import com.ssafy.devlog.service.PortfolioProjectService;
import com.ssafy.devlog.service.ProjectRoleService;
import com.ssafy.devlog.service.ProjectStackService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/portfoliopjt")
public class PortfolioProjectController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PortfolioProjectService portfolioProjectService;
	@Autowired
	private ProjectRoleService projectRoleService;
	@Autowired
	private ProjectStackService projectStackService;
	
	@ApiOperation(value = "포트폴리오의 모든 프로젝들을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_portfolio}")
	public ResponseEntity<List<ProjectWithRoleStack>> selectAllPortfolioProject(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioProject - 호출");
		
		List<Project> projectList = portfolioProjectService.selectAllPortfolioProject(seq_post_portfolio);
		List<ProjectWithRoleStack> projectWithRoleStackList = new ArrayList<ProjectWithRoleStack>();
		for(int i=0,size=projectList.size();i<size;i++) {
			int seq_post_project = projectList.get(i).getSeq();
			List<ProjectRole> projectRoleList = projectRoleService.selectAllProjectRole(seq_post_project);
			List<ProjectStack> projectStackList = projectStackService.selectAllProjectStack(seq_post_project);
			projectWithRoleStackList.add(new ProjectWithRoleStack(projectList.get(i), projectRoleList, projectStackList));
		}
		
		return new ResponseEntity<List<ProjectWithRoleStack>>(projectWithRoleStackList, HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오에 프로젝트를 추가하거나 삭제한다(seq_post_project == null >> 해당 포트폴리오 연관 프로젝트 전체 삭제) ex) {seq_post_portfolio : 4, seq_post_project :[3,16,42,62]}", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPortfolioProject(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("insertPortfolioProject - 호출");
		List<Integer> seq_post_project = (List<Integer>)params.get("seq_post_project");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioProjectService.deleteAllPortfolioProject(seq_post_portfolio);
		if(seq_post_project == null || seq_post_project.size()==0)
			return new ResponseEntity<String>("null", HttpStatus.OK);
		else if(portfolioProjectService.insertPortfolioProject(seq_post_portfolio,seq_post_project)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
	
	@ApiOperation(value = "포트폴리오의 프로젝트를 삭제한다.", response = String.class)
	@DeleteMapping(value = "/{seq_post_portfolio}/{seq_post_project}")
	public ResponseEntity<String> deletePortfolioProject(@PathVariable int seq_post_portfolio, @PathVariable int seq_post_project) throws Exception {
		logger.debug("deletePortfolioProject - 호출");
		if(portfolioProjectService.deletePortfolioProject(seq_post_portfolio, seq_post_project)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
