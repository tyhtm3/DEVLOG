package com.ssafy.devlog.controller;

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

import com.ssafy.devlog.dto.PostLike;
import com.ssafy.devlog.service.PostLikeService;
import com.ssafy.devlog.service.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/postlike")
public class PostLikeController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostLikeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PostLikeService postLikeService;
	@Autowired
	private PostService postService;
	
	@ApiOperation(value = "포스트에 유저가 누른 좋아요 여부 검사", response = PostLike.class)
	@GetMapping(value = "/{seq_post}/{seq_user}")
	public ResponseEntity<PostLike> selectPostLikeByUser(@PathVariable int seq_post, @PathVariable int seq_user) throws Exception {
		logger.debug("selectPostLikeByUser - 호출");
		return new ResponseEntity<PostLike>(postLikeService.selectPostLikeByUser(seq_post,seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트의 모든 좋아요 개수를 반환", response = Integer.class)
	@GetMapping(value = "/{seq_post}")
	public ResponseEntity<Integer> selectPostLikeCount(@PathVariable int seq_post) throws Exception {
		logger.debug("selectPostLikeCount - 호출");
		return new ResponseEntity<Integer>(postLikeService.selectPostLikeCount(seq_post), HttpStatus.OK);
	}
	
	@ApiOperation(value = "포스트에 좋아요를 추가한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPostLike(@RequestBody PostLike postLike) throws Exception {
		logger.debug("inserPostLike - 호출");
		if(postLikeService.updatePostLikeCount(postLike.getSeq(), 1)==1&&postLikeService.insertPostLike(postLike)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "포스트에 누른 좋아요를 취소한다.", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePostLike(@PathVariable int seq) throws Exception {
		logger.debug("deletePostLike - 호출");
	
		if(postLikeService.updatePostLikeCount(seq, -1)==1&&postLikeService.deletePostLike(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}

