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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.PostTag;
import com.ssafy.devlog.service.PostTagService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/posttag")
public class PostTagController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostTagController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PostTagService postTagService;
	
	@ApiOperation(value = "포스트의 모든 태그를 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post}")
	public ResponseEntity<List<PostTag>> selectAllPostTag(@PathVariable int seq_post) throws Exception {
		logger.debug("selectAllPostTag - 호출");
		return new ResponseEntity<List<PostTag>>(postTagService.selectAllPostTag(seq_post), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트의 태그를 추가한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPostTag(@RequestBody PostTag postTag) throws Exception {
		logger.debug("inserPostTag - 호출");
		if(postTagService.insertPostTag(postTag)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "포스트의 태그를 삭제한다.", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePostTag(@PathVariable int seq) throws Exception {
		logger.debug("deletePostTag - 호출");
		if(postTagService.deletePostTag(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
