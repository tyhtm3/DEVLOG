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
	
	
	/* 	비로그인 (seq_user==0) : 공개범위가 '전체'인 포스트 반환
	 * 	   로그인 (seq_user!=0) : 공개범위가 '전체'인 포스트와 '이웃공개/비공개'인 포스트의 접근여부 판별하여 반환  */
	
	
	
	/* show feed post */
	@ApiOperation(value = "피드에서 모든 포스트 반환.", response = List.class)
	@GetMapping(value = "/feed/post/{seq_user}")
	public ResponseEntity<List<Post>> selectAllPost(@PathVariable int seq_user) throws Exception {
		logger.debug("selectAllPost - 호출");
		return new ResponseEntity<List<Post>>(postService.selectAllPost(seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드에서 이웃 블로그의 모든 포스트를 반환.", response = List.class)
	@GetMapping(value = "/feed/post/neighbor/{seq_user}")
	public ResponseEntity<List<Post>> selectAllPostByNeighbor(@PathVariable int seq_user) throws Exception {
		logger.debug("selectAllPostByNeighbor - 호출");
		return new ResponseEntity<List<Post>>(postService.selectAllPostByNeighbor(seq_user), HttpStatus.OK);
	}

	// Map을 받아오기 때문에 Get 대신 Post를 사용함.
	@ApiOperation(value = "피드에서 선택된 태그를 가진 모든 포스트를 반환. (ex. 'seq_user':1 , 'tag': {'java','mysql'} )", response = List.class)    
	@PostMapping(value = "/feed/post/tag")
	public ResponseEntity<List<Post>> selectAllPostByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllPostByTag - 호출");
		int seq_user = (params.get("seq_user")==null?0:(int)params.get("seq_user"));
		@SuppressWarnings("unchecked")
		List<String> tag = (List<String>)params.get("tag");
		return new ResponseEntity<List<Post>>(postService.selectAllPostByTag(seq_user,tag), HttpStatus.OK);
	}

	
	
	
	
	/* show blog post */
	@ApiOperation(value = "블로그 메인에서 모든 포스트를 반환.", response = List.class)    
	@GetMapping(value = "/blog/post/{seq_blog}/{seq_user}")
	public ResponseEntity<List<Post>> selectAllPostByBlog(@PathVariable int seq_blog, @PathVariable int seq_user) {
		logger.debug("selectAllPostByBlog - 호출");
		return new ResponseEntity<List<Post>>(postService.selectAllPostByBlog(seq_user, seq_blog), HttpStatus.OK);
	}
	
	// Map을 받아오기 때문에 Get 대신 Post를 사용함.
	@ApiOperation(value = "블로그 메인에서 선택한 태그에 해당하는 모든 포스트를 반환 (ex. 'seq_user':0 , 'tag': {'java','mysql'} )", response = List.class)    
	@PostMapping(value = "/blog/post/tag")
	public ResponseEntity<List<Post>> selectAllPostByBlogByTag(@RequestBody Map<String, Object> params) {
		logger.debug("selectAllPostByBlogByTag - 호출");
		int seq_user = (params.get("seq_user")==null?0:(int)params.get("seq_user"));
		return new ResponseEntity<List<Post>>(postService.selectAllPostByBlogByTag(seq_user,(int)params.get("seq_blog"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	

	/* basic post crud */
	@ApiOperation(value = "글번호에 해당하는 게시글을 반환", response = String.class)
	@GetMapping(value = "/post/{seq}")
	public ResponseEntity<Post> selectPost(@PathVariable int seq) {
		logger.debug("selectPost - 호출");
		return new ResponseEntity<Post>(postService.selectPost(seq), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "새로운 게시글 입력 ( seq_blog, title, disclosure, content ) 만 사용 ", response = String.class)
	@PostMapping(value = "/post")
	public ResponseEntity<String> insertPost(@RequestBody Post post) {
		
		logger.debug("insertPost - 호출");
		
		// insertPost 이후 postBasic 객체에 seq 받아오기 위한 작업
		Post postBasic = new Post();
		postBasic.setSeq_blog(post.getSeq_blog());
		postBasic.setTitle(post.getTitle());
		postBasic.setDisclosure(post.getDisclosure());
		postBasic.setContent(post.getContent());
		postService.insertPost(postBasic);
		
		if (postService.insertPostBasic(postBasic)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시글 수정 ( seq, title, disclosure, content ) 만 사용", response = String.class)
	@PutMapping(value = "/post")
	public ResponseEntity<String> updatePost(@RequestBody Post post) {
		logger.debug("updatePost - 호출");
		if (postService.updatePost(post)==1&&postService.updatePostBasic(post)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글 삭제", response = String.class)
	@DeleteMapping(value = "/post/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable int seq) {
		logger.debug("deletePost - 호출");
		if (postService.deletePost(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	

}
