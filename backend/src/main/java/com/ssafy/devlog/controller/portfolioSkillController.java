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

import com.ssafy.devlog.dto.PortfolioSkill;
import com.ssafy.devlog.service.PortfolioSkillService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/portfolio/skill")
public class portfolioSkillController {

	private static final Logger logger = LoggerFactory.getLogger(portfolioSkillController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PortfolioSkillService portfolioSkillService;
	
	@ApiOperation(value = "포트폴리오의 기술 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_portfolio}")
	public ResponseEntity<List<PortfolioSkill>> selectAllPortfolioSkill(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioSkill - 호출");
		return new ResponseEntity<List<PortfolioSkill>>(portfolioSkillService.selectAllPortfolioSkill(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 기술을 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"skill\" : [ {\"skill\" : \"java\",\"level\":\"5\"},{\"skill\" : \"c++\",\"level\" : \"5\"} ] } ", response = String.class)
	@PostMapping
	public ResponseEntity<String> updatePortfolioSkill(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePortfolioSkill - 호출");
		List<Object> skill = (List<Object>)params.get("skill");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioSkillService.deletePortfolioSkill(seq_post_portfolio);
		if(skill==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(portfolioSkillService.insertPortfolioSkill(seq_post_portfolio,skill)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
}