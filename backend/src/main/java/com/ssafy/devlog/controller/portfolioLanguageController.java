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

import com.ssafy.devlog.dto.PortfolioLanguage;
import com.ssafy.devlog.service.PortfolioLanguageService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/portfolio/language")
public class portfolioLanguageController {

	private static final Logger logger = LoggerFactory.getLogger(portfolioLanguageController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PortfolioLanguageService portfolioLanguageService;
	
	@ApiOperation(value = "포트폴리오의 언어 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_portfolio}")
	public ResponseEntity<List<PortfolioLanguage>> selectAllPortfolioLanguagel(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioLanguage - 호출");
		return new ResponseEntity<List<PortfolioLanguage>>(portfolioLanguageService.selectAllPortfolioLanguage(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 언어를 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"language\" : [ {\"language\" : \"English\",\"level\":\"5\"},{\"language\" : \"French\",\"level\" : \"3\"} ] } ", response = String.class)
	@PostMapping
	public ResponseEntity<String> updatePortfolioLanguage(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePortfolioLanguage - 호출");
		List<Object> language = (List<Object>)params.get("language");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioLanguageService.deletePortfolioLanguage(seq_post_portfolio);
		if(language==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(portfolioLanguageService.insertPortfolioLanguage(seq_post_portfolio,language)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
}