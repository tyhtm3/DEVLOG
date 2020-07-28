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
@RequestMapping(value="/api/post")
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private PostService postService;
	
	
	/* basic post crud */
	@ApiOperation(value = "글번호에 해당하는 게시글을 반환", response = String.class)
	@GetMapping(value = "/{seq}")
	public ResponseEntity<Post> selectPost(@PathVariable int seq) {
		logger.debug("selectPost - 호출");
		return new ResponseEntity<Post>(postService.selectPost(seq), HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "새로운 게시글 입력 ( seq_blog, title, disclosure, content ) 만 사용 ", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPost(@RequestBody Post post) {
		
		logger.debug("insertPost - 호출");
		
		// insertPost 이후 postBasic 객체에 seq 받아오기 위한 작업
		Post postBasic = new Post();
		postBasic = post;
		postService.insertPost(postBasic);
		
		if (postService.insertPostBasic(postBasic)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시글 수정 ( seq, title, disclosure, content ) 만 사용", response = String.class)
	@PutMapping
	public ResponseEntity<String> updatePost(@RequestBody Post post) {
		logger.debug("updatePost - 호출");
		if (postService.updatePost(post)==1&&postService.updatePostBasic(post)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글번호에 해당하는 게시글 삭제", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable int seq) {
		logger.debug("deletePost - 호출");
		if (postService.deletePost(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}	
	
	
	
	/* 	비로그인 (seq_user==0)		: 공개범위가 '전체'인 포스트 반환
	 * 	   로그인 (seq_user!=0)		: 공개범위가 '전체'인 포스트와 '이웃공개/비공개'인 포스트의 접근여부 판별하여 반환 
	 *   전체글 (disclosure==1)	: 전체 글 보기
	 *   이웃글 (disclosure==2)	: 이웃의 글 보기
	 *  */
	
	// RequestBody로 Map을 받아오기 때문에 Get 대신 Post를 사용함.
	
	// show feed
	@ApiOperation(value = "피드에서 모든 포스트의 개수 반환. (ex. { seq_user:2 , seq_blog:1, tag:['python','java']  } )", response = List.class)
	@PostMapping(value = "/feed/count")
	public ResponseEntity<Integer> selectPostCntByFeed(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("selectPostCntByFeed - 호출");
		return new ResponseEntity<Integer>(postService.selectPostCntByFeed((int)params.get("seq_user"),(int)params.get("disclosure"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "피드에서 한 페이지의 포스트 반환. (ex. { seq_user:1 , seq_blog:1, offset:0, limit:10 tag:['python']  } )", response = List.class)
	@PostMapping(value = "/feed")
	public ResponseEntity<List<Post>> selectPostByFeed(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("selectPostByFeed - 호출");
		return new ResponseEntity<List<Post>>(postService.selectPostByFeed((int)params.get("seq_user"),(int)params.get("disclosure"),(int)params.get("offset"),(int)params.get("limit"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	
	
	// show blog
	@ApiOperation(value = "블로그 메인에서 모든 포스트의 개수 반환. (ex.  { seq_user:0 , seq_blog:1, tag:['java']  }   )", response = List.class)    
	@PostMapping(value = "/blog/count")
	public ResponseEntity<Integer> selectPostCntByBlog(@RequestBody Map<String, Object> params) {
		logger.debug("selectPostCntByBlog - 호출");
		return new ResponseEntity<Integer>(postService.selectPostCntByBlog((int)params.get("seq_user"),(int)params.get("seq_blog"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "블로그 메인에서 한 페이지의 포스트 반환. (ex. { seq_user:1 , seq_blog:1, offset:0, limit:10, tag:['python','c++']  } )", response = List.class)    
	@PostMapping(value = "/blog")
	public ResponseEntity<List<Post>> selectPostByBlog(@RequestBody Map<String, Object> params) {
		logger.debug("selectPostByBlog - 호출");
		return new ResponseEntity<List<Post>>(postService.selectPostByBlog((int)params.get("seq_user"),(int)params.get("seq_blog"),(int)params.get("offset"),(int)params.get("limit"),(List<String>)params.get("tag")), HttpStatus.OK);
	}
	
	



	
}
