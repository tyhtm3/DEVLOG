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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.PostTag;
import com.ssafy.devlog.dto.UserTag;
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
	
	@ApiOperation(value = "포스트의 태그를 등록하거나 수정한다. (ex. { seq_post:1 , tag:['python','c++']  } )", response = String.class)
	@PostMapping
	public ResponseEntity<String> updatePostTag(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("updatePostTag - 호출");
		List<String> tag = (List<String>)params.get("tag");
		int seq_post = (int)params.get("seq_post");
		postTagService.deletePostTag(seq_post);
		if(tag==null)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else if(postTagService.insertPostTag(seq_post,tag)!=0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		else 
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		
	}
	
}
