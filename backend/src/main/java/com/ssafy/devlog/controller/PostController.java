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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.service.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value="/api")
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PostService postService;
	
	
	/* 	비로그인 	: params.get("seq_user")==null 로 판별하여 공개범위가 (전체)인 포스트만 반환
	 * 	로그인 	: 						!=null 로 판별하여 공개범위가 (전체) 포스트 or 공개범위가 (이웃공개/비공개)인 포스트의 접근여부 판별하여 반환  */
	
	
	/* show feed post */
	@ApiOperation(value = "피드에서 모든 포스트 반환", response = List.class)
	@GetMapping(value = "/feed/post")
	public ResponseEntity<List<Post>> selectAllPost(@RequestBody Map<String, Integer> params) throws Exception {
		logger.debug("selectAllPost - 호출");
		int seq_user = (params.get("seq_user")==null?0:params.get("seq_user"));
		return new ResponseEntity<List<Post>>(postService.selectAllPost(seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드에서 이웃 블로그의 모든 포스트를 반환", response = List.class)
	@GetMapping(value = "/feed/post/neighbor/{seq_user}")
	public ResponseEntity<List<Post>> selectAllPostByNeighbor(@PathVariable int seq_user) throws Exception {
		logger.debug("selectAllPostByNeighbor - 호출");
		return new ResponseEntity<List<Post>>(postService.selectAllPostByNeighbor(seq_user), HttpStatus.OK);
	}

	
	@ApiOperation(value = "피드에서 선택된 태그를 가진 모든 포스트를 반환", response = List.class)    
	@GetMapping(value = "/feed/post/tag")
	public ResponseEntity<List<Post>> selectAllPostByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllPostByTag - 호출");
		int seq_user = (params.get("seq_user")==null?0:(int)params.get("seq_user"));
		@SuppressWarnings("unchecked")
		List<String> tag = (List<String>)params.get("tag");
		return new ResponseEntity<List<Post>>(postService.selectAllPostByTag(seq_user,tag), HttpStatus.OK);
	}

	
	
	/* show blog post */
	@ApiOperation(value = "블로그 메인에서 모든 포스트를 반환", response = List.class)    
	@GetMapping(value = "/blog/post")
	public ResponseEntity<List<Post>> selectAllPostByBlog(@RequestBody Map<String, Integer> params) {
		logger.debug("selectAllPostByBlog - 호출");
		int seq_user = (params.get("seq_user")==null?0:params.get("seq_user"));
		return new ResponseEntity<List<Post>>(postService.selectAllPostByBlog(seq_user, params.get("seq_blog")), HttpStatus.OK);
	}
	
	@ApiOperation(value = "블로그 메인에서 선택한 태그에 해당하는 모든 포스트를 반환", response = List.class)    
	@GetMapping(value = "/blog/post/tag")
	public ResponseEntity<List<Post>> selectAllPostByBlogByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllPostByBlogByTag - 호출");
		int seq_user = (params.get("seq_user")==null?0:(int)params.get("seq_user"));
		return new ResponseEntity<List<Post>>(postService.selectAllPostByBlogByTag(seq_user,(int)params.get("seq_blog"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	
	
	
	/* basic post crud */
	@ApiOperation(value = "글번호에 해당하는 게시글을 반환한다", response = String.class)
	@GetMapping(value = "/post/{seq}")
	public ResponseEntity<Post> selectPost(@PathVariable int seq) {
		logger.debug("selectPost - 호출");
		return new ResponseEntity<Post>(postService.selectPost(seq), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "새로운 게시글을 입력한다.", response = String.class)
	@PostMapping(value = "/post")
	public ResponseEntity<String> insertPost(@RequestBody Post post) {
		logger.debug("insertPost - 호출");
		Post postBasic = new Post();
		postBasic.setSeq(postService.insertPost(post));
		postBasic.setContent(post.getContent());
		if (postService.insertPostBasic(postBasic)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시글을 수정한다.", response = String.class)
	@PutMapping(value = "/post")
	public ResponseEntity<String> updatePost(@RequestBody Post post) {
		logger.debug("updatePost - 호출");
		Post postBasic = new Post();
		postBasic.setSeq(postService.updatePost(post));
		postBasic.setContent(post.getContent());
		if (postService.updatePostBasic(postBasic)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글을 삭제한다.", response = String.class)
	@DeleteMapping(value = "/post/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable int seq) {
		logger.debug("deletePost - 호출");
		if (postService.deletePost(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	

}
