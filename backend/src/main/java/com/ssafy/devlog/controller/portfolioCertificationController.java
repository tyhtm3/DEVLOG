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

import com.ssafy.devlog.dto.PortfolioCertification;
import com.ssafy.devlog.service.PortfolioCertificationService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/portfolio/certification")
public class portfolioCertificationController {

	private static final Logger logger = LoggerFactory.getLogger(portfolioCertificationController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PortfolioCertificationService portfolioCertificationService;
	
	@ApiOperation(value = "포트폴리오의 자격증 목록을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post_portfolio}")
	public ResponseEntity<List<PortfolioCertification>> selectAllPortfolioCertification(@PathVariable int seq_post_portfolio) throws Exception {
		logger.debug("selectAllPortfolioCertification - 호출");
		return new ResponseEntity<List<PortfolioCertification>>(portfolioCertificationService.selectAllPortfolioCertification(seq_post_portfolio), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포트폴리오의 자격증을 입력하거나 수정한다. { \"seq_post_portfolio\" : 395 , \"certification\" : [{\"certification\" : \"정보처리기사\",\"acq_date\" : \"2018-10\"}] } ", response = String.class)
	@PostMapping
	public ResponseEntity<String> updatePortfolioCertification(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePortfolioCertification - 호출");
		List<Object> certification = (List<Object>)params.get("certification");
		int seq_post_portfolio = (int)params.get("seq_post_portfolio");
		portfolioCertificationService.deletePortfolioCertification(seq_post_portfolio);
		if(certification==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(portfolioCertificationService.insertPortfolioCertification(seq_post_portfolio,certification)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
}