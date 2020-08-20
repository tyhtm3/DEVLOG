package com.ssafy.devlog.controller;

import java.util.List;

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

import com.ssafy.devlog.dto.Portfolio;
import com.ssafy.devlog.service.PortfolioService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value="/api/portfolio")
public class PortfolioController {

	private static final Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PortfolioService portfolioService;
	

	/* basic portfolio crud */
	@ApiOperation(value = "글번호에 해당하는 포트폴리오를 반환", response = String.class)
	@GetMapping(value = "/{seq}")
	public ResponseEntity<Portfolio> selectPortfolio(@PathVariable int seq) {
		logger.debug("selectPortfolio - 호출");
		return new ResponseEntity<Portfolio>(portfolioService.selectPortfolio(seq), HttpStatus.OK);
		
	}
	

	
	@ApiOperation(value = "새로운  포트폴리오 입력  ", response = String.class)
	@PostMapping
	public ResponseEntity<Integer> insertPortfolio(@RequestBody Portfolio portfolio) {
		
		logger.debug("insertPortfolio - 호출");

		// insertPortfolio 이후 portfolioBasic 객체에 seq 받아오기 위한 작업
		Portfolio pf = portfolio;
		if(portfolio.getRepresentation()==1)
			portfolioService.updatePortfolioGeneral(portfolio.getSeq_blog());
			
		portfolioService.insertPost(pf);
		int seq_post_portfolio = pf.getSeq();
		
		if (portfolioService.insertPostPortfolio(pf)==1) {
			return new ResponseEntity<Integer>(seq_post_portfolio, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(204, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = " 포트폴리오 수정", response = String.class)
	@PutMapping
	public ResponseEntity<String> updatePortfolio(@RequestBody Portfolio portfolio) {
		logger.debug("updatePortfolio - 호출");
		
		if(portfolio.getRepresentation()==1)
			portfolioService.updatePortfolioGeneral(portfolio.getSeq_blog());
		
		if (portfolioService.updatePost(portfolio)==1&&portfolioService.updatePostPortfolio(portfolio)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "대표 포트폴리오 변경 ( seq_blog : 101, seq : 520 )", response = String.class)
	@PutMapping(value = "/representation")
	public ResponseEntity<String> updatePortfolioRepresentation(@RequestBody Portfolio portfolio) {
		logger.debug("updatePortfolioRepresentation - 호출");
		
		portfolioService.updatePortfolioGeneral(portfolio.getSeq_blog());
		if (portfolioService.updatePortfolioRepresentation(portfolio.getSeq())==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	@ApiOperation(value = "블로그의 대표 포트폴리오 시퀀스를 반환", response = String.class)
	@GetMapping(value = "/representation/{seq_blog}")
	public ResponseEntity<Integer> selectPortfolioByRep(@PathVariable int seq_blog) {
		logger.debug("selectPortfolioByRep - 호출");
		System.out.println(seq_blog);
		return new ResponseEntity<Integer>(portfolioService.selectPortfolioByRep(seq_blog).getSeq(), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "글번호에 해당하는 포트폴리오 삭제", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePortfolio(@PathVariable int seq) {
		logger.debug("deletePortfolio - 호출");
		if (portfolioService.deletePortfolio(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	
	/* 	비로그인 (seq_user==0)		: 공개범위가 '전체'인 포스트 반환
	 * 	   로그인 (seq_user!=0)		: 공개범위가 '전체'인 포스트와 '이웃공개/비공개'인 포스트의 접근여부 판별하여 반환 
	 *  */


	@ApiOperation(value = "블로그 메인에서 한 페이지의 포트폴리오를 반환. (ex. { seq_user:1 , seq_blog:1, offset:0, limit:6 } )", response = List.class)    
	@GetMapping(value = "/blog/{seq_user}/{seq_blog}/{offset}/{limit}")
	public ResponseEntity<List<Portfolio>> selectPortfolioByBlog(@PathVariable int seq_user,@PathVariable int seq_blog,@PathVariable int offset,@PathVariable int limit) {
		logger.debug("selectPortfolioByBlog - 호출");
		return new ResponseEntity<List<Portfolio>>(portfolioService.selectPortfolioByBlog(seq_user, seq_blog,offset,limit), HttpStatus.OK);
	}
	

}
