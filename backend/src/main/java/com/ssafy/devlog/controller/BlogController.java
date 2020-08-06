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

import com.ssafy.devlog.dto.Blog;
import com.ssafy.devlog.service.BlogService;
import com.ssafy.devlog.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/blog")
public class BlogController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private JwtService jwtService;
	
	@ApiOperation(value = "모든 블로그를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Blog>> selectAllBlog() throws Exception {
		logger.debug("selectAllBlog - 호출");
		return new ResponseEntity<List<Blog>>(blogService.selectAllBlog(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 유저의 블로그를 반환한다.", response = List.class)
	@GetMapping("{seq}")
	public ResponseEntity<Blog> selectBlogByUser(@PathVariable int seq) throws Exception {
		logger.debug("selectBlogByUser - 호출");
		return new ResponseEntity<Blog>(blogService.selectBlogByUser(seq), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 블로그를 생성한다", response = List.class)
	@PostMapping
	public ResponseEntity<String> insertBlog(@RequestBody Blog blog) throws Exception {
		logger.debug("insertBlog - 호출");
		if(blogService.insertBlog(blog)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "블로그 정보를 수정한다", response = List.class)
	@PutMapping
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog) throws Exception {
		logger.debug("updateBlog - 호출");
		if(blogService.updateBlog(blog)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "블로그를 삭제한다.", response = List.class)
	@DeleteMapping
	public ResponseEntity<String> deleteBlog() throws Exception {
		logger.debug("deleteBlog - 호출");
		int seq = jwtService.getSeq();
		if(blogService.deleteBlog(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
