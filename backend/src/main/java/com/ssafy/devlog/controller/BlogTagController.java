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

import com.ssafy.devlog.dto.BlogTag;
import com.ssafy.devlog.dto.PostTag;
import com.ssafy.devlog.service.BlogTagService;
import com.ssafy.devlog.service.JwtService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/blogtag")
public class BlogTagController {
	
	private static final Logger logger = LoggerFactory.getLogger(BlogTagController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BlogTagService blogTagService;
	
	@Autowired
	private JwtService jwtService;
	
	@ApiOperation(value = "모든 태그를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BlogTag>> selectAllBlogTag() throws Exception {
		logger.debug("selectAllTag - 호출");
		return new ResponseEntity<List<BlogTag>>(blogTagService.selectAllBlogTag(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 블로그의 태그를 반환한다.", response = List.class)
	@GetMapping("{blog_seq}")
	public ResponseEntity<List<BlogTag>> selectBlogTagByBlog(@PathVariable int blog_seq) throws Exception {
		logger.debug("selectBlogTagByUser - 호출");
		return new ResponseEntity<List<BlogTag>>(blogTagService.selectBlogTagByBlog(blog_seq), HttpStatus.OK);
	}
	
	@ApiOperation(value = "블로그 대표 태그를 생성한다 //중복 입력시 401", response = List.class)
	@PostMapping
	public ResponseEntity<String> insertBlogTag(@RequestBody BlogTag blogTag) throws Exception {
		logger.debug("inserBlogTag - 호출");
		blogTag.setSeq_blog(jwtService.getSeq());
		BlogTag check = blogTagService.selectBlogTagByBlogAndTag(blogTag);
		if (check != null)
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		else if(blogTagService.insertBlogTag(blogTag)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "특정 태그를 삭제한다.", response = List.class)
	@DeleteMapping("{seq}")
	public ResponseEntity<String> deleteBlogTag(@PathVariable int seq) throws Exception {
		logger.debug("deleteBlogTag - 호출");
		if(blogTagService.deleteBlogTag(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
