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

import com.ssafy.devlog.dto.PostComment;
import com.ssafy.devlog.service.PostCommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/postcomment")
public class PostCommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PostCommentService postCommentService;
	
	@ApiOperation(value = "포스트의 모든 댓글들을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post}")
	public ResponseEntity<List<PostComment>> selectAllPostComment(@PathVariable int seq_post) throws Exception {
		logger.debug("selectAllPostComment - 호출");
		return new ResponseEntity<List<PostComment>>(postCommentService.selectAllPostComment(seq_post), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트에 댓글을 입력한다.", response = List.class)
	@PostMapping
	public ResponseEntity<String> insertPostComment(@RequestBody PostComment postComment) throws Exception {
		logger.debug("inserPostComment - 호출");
		if(postCommentService.insertPostComment(postComment)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "포스트 댓글을 수정한다.", response = List.class)
	@PutMapping
	public ResponseEntity<String> updatePostComment(@RequestBody PostComment postComment) throws Exception {
		logger.debug("updatePostComment - 호출");
		if(postCommentService.updatePostComment(postComment)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "포스트 댓글을 삭제한다.", response = List.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePostComment(@PathVariable int seq) throws Exception {
		logger.debug("deletePostComment - 호출");
		if(postCommentService.deletePostComment(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
