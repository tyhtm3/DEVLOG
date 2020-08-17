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

import com.ssafy.devlog.dto.PortfolioEducation;
import com.ssafy.devlog.dto.PortfolioExperience;
import com.ssafy.devlog.dto.PortfolioInfo;
import com.ssafy.devlog.dto.PortfolioSkill;
import com.ssafy.devlog.dto.PortfolioSocial;
import com.ssafy.devlog.service.PortfolioInfoService;
import com.ssafy.devlog.service.PortfolioSkillService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/portfolio/info")
public class portfolioInfoController {

	private static final Logger logger = LoggerFactory.getLogger(portfolioInfoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PortfolioInfoService portfolioInfoService;
	
	@ApiOperation(value = "포트폴리오의 기본정보를 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_portfolio}")
	public ResponseEntity<PortfolioInfo> selectAllPortfolioInfo(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioInfo - 호출");
		return new ResponseEntity<PortfolioInfo>(portfolioInfoService.selectAllPortfolioInfo(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 기본정보를 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"introduction\" : \"자기소개\",\"address\" : \"서울시 강남구 역삼동 멀티캠퍼스\" ,\"objective\" : \"희망 직무\"} ", response = String.class)
	@PostMapping
	public ResponseEntity<String> updatePortfolioSkill(@RequestBody PortfolioInfo portfolioInfo) throws Exception {
		logger.debug("updatePortfolioInfo - 호출");
		portfolioInfoService.deletePortfolioInfo(portfolioInfo.getSeq_post_portfolio());
		if(portfolioInfoService.insertPortfolioInfo(portfolioInfo)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
	}
	@ApiOperation(value = "포트폴리오의 학력을 반환한다.", response = List.class)
	@GetMapping(value = "education/{seq_post_portfolio}")
	public ResponseEntity<List<PortfolioEducation>> selectAllPortfolioEducation(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioSkill - 호출");
		return new ResponseEntity<List<PortfolioEducation>>(portfolioInfoService.selectAllPortfolioEducation(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 학력을 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"education\" : [ {\"major\" : \"컴퓨터 소프트웨어학과\",\"name\":\"광운대학교\",\"start\":\"2013-03\",\"end\":\"2018-02\"},"
			+ "{\"major\" : \"고등학교\",\"name\":\"분당고등학교\",\"start\":\"2010-03\",\"end\":\"2013-02\"} ] } ", response = String.class)
	@PostMapping(value = "education")
	public ResponseEntity<String> updatePortfolioEducation(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePortfolioEducation - 호출");
		List<Object> education = (List<Object>)params.get("education");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioInfoService.deletePortfolioEducation(seq_post_portfolio);
		if(education==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(portfolioInfoService.insertPortfolioEducation(seq_post_portfolio,education)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
	@ApiOperation(value = "포트폴리오의 경력을 반환한다.", response = List.class)
	@GetMapping(value = "experience/{seq_post_portfolio}")
	public ResponseEntity<List<PortfolioExperience>> selectAllPortfolioExperience(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioExperience - 호출");
		return new ResponseEntity<List<PortfolioExperience>>(portfolioInfoService.selectAllPortfolioExperience(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 경력을 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"experience\" : [ {\"position\" : \"웹디자인\",\"company\" : \"삼성전자\", \"start\" : \"2020-04\",\"end\" : \"\"} ] } ", response = String.class)
	@PostMapping( value = "experience")
	public ResponseEntity<String> updatePortfolioExperience(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePortfolioExperience - 호출");
		List<Object> experience = (List<Object>)params.get("experience");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioInfoService.deletePortfolioExperience(seq_post_portfolio);
		if(experience==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(portfolioInfoService.insertPortfolioExperience(seq_post_portfolio,experience)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
	@ApiOperation(value = "포트폴리오의 소셜 목록을 반환한다.", response = List.class)
	@GetMapping(value = "social/{seq_post_portfolio}")
	public ResponseEntity<List<PortfolioSocial>> selectAllPortfolioSocial(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioSocial - 호출");
		return new ResponseEntity<List<PortfolioSocial>>(portfolioInfoService.selectAllPortfolioSocial(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 소셜 목록을 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"social\" : [ { \"link\" : \"https://www.facebook.com/ \" , "
			+ " \"icon\": \"ti-facebook\" },{\"link\" : \"https://www.instagram.com/ \",\"icon\" : \"ti-instagram\"} ] } ", response = String.class)
	@PostMapping(value = "social")
	public ResponseEntity<String> updatePortfolioSocial(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePortfolioSocial - 호출");
		List<Object> social = (List<Object>)params.get("social");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioInfoService.deletePortfolioSocial(seq_post_portfolio);
		if(social==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(portfolioInfoService.insertPortfolioSocial(seq_post_portfolio,social)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
}