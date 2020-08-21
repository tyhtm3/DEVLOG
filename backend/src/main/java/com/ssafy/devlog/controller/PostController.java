package com.ssafy.devlog.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.dto.PostTag;
import com.ssafy.devlog.dto.PostWithTag;
import com.ssafy.devlog.dto.User;
import com.ssafy.devlog.service.JwtService;
import com.ssafy.devlog.service.PostCommentService;
import com.ssafy.devlog.service.PostService;
import com.ssafy.devlog.service.PostTagService;
import com.ssafy.devlog.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value = "/api/post")
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PostService postService;
	@Autowired
	private PostTagService postTagService;
	@Autowired
	private PostCommentService postCommentService;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;

	/* basic post crud */
	@ApiOperation(value = "글번호에 해당하는 게시글을 반환", response = String.class)
	@GetMapping(value = "/{seq}")
	public ResponseEntity<Post> selectPost(@PathVariable int seq) {
		logger.debug("selectPost - 호출");
		return new ResponseEntity<Post>(postService.selectPost(seq), HttpStatus.OK);

	}

	@ApiOperation(value = "임시 보관 게시글을 반환", response = String.class)
	@GetMapping(value = "/draft")
	public ResponseEntity<List<Post>> selectDraftPost() {
		logger.debug("selectDraftPost - 호출");
		return new ResponseEntity<List<Post>>(postService.selectDraftPost(jwtService.getSeq()), HttpStatus.OK);

	}
	
	@ApiOperation(value = "content, title, disclousre, img_url, regtime(2100-02-02 01:01:01) or null, status(draft or published)", response = String.class)
	@PostMapping
	public ResponseEntity<Integer> insertPost(@RequestBody Post post) {

		logger.debug("insertPost - 호출");

		// insertPost 이후 postBasic 객체에 seq 받아오기 위한 작업
		Post postBasic = new Post();
		postBasic = post;
		postBasic.setSeq_blog(jwtService.getSeq());
		postService.insertPost(postBasic);

		if (postService.insertPostBasic(postBasic) == 1) {
			return new ResponseEntity<Integer>(postBasic.getSeq(), HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "seq, content, title, disclousre, img_url, regtime(2020-02-02 01:01:01)", response = String.class)
	@PutMapping
	public ResponseEntity<String> updatePost(@RequestBody Post post) {
		logger.debug("updatePost - 호출");
		if (postService.updatePost(post) == 1 && postService.updatePostBasic(post) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글 삭제", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePost(@PathVariable int seq) {
		logger.debug("deletePost - 호출");
		if (postService.deletePost(seq) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	/*
	 * 비로그인 (seq_user==0) : 공개범위가 '전체'인 포스트 반환 로그인 (seq_user!=0) : 공개범위가 '전체'인 포스트와
	 * '이웃공개/비공개'인 포스트의 접근여부 판별하여 반환 전체글 (disclosure==1) : 전체 글 보기 이웃글
	 * (disclosure==2) : 이웃의 글 보기
	 */

	// RequestBody로 Map을 받아오기 때문에 Get 대신 Post를 사용함.

	// show feed
	@ApiOperation(value = "피드에서 한 페이지의 포스트 반환. (ex. { seq_user:1 , disclosure:1, offset:0, limit:6, tag:['python']  } )", response = List.class)
	@PostMapping(value = "/feed")
	public ResponseEntity<List<PostWithTag>> selectPostByFeed(@RequestBody Map<String, Object> params) throws Exception {
		int seq_user = jwtService.getSeq();
		logger.debug("selectPostByFeed - 호출");
		List<Post> postList= postService.selectPostByFeed(seq_user, (int) params.get("disclosure"),
				(int) params.get("offset"), (int) params.get("limit"), (List<String>) params.get("tag"));
		
		List<PostWithTag> postWithTagList = new ArrayList<PostWithTag>();
		for(int i=0,size=postList.size();i<size;i++) {
			int seq = postList.get(i).getSeq();
			List<PostTag> tags = postTagService.selectAllPostTag(seq);
			int comment_count = postCommentService.selectPostCommentCnt(seq);
			int seq_blog = postService.selectPost(seq).getSeq_blog();
			User user = userService.selectUserBySeq(seq_blog);
			postWithTagList.add(new PostWithTag(postList.get(i),tags,comment_count,user.getId(),user.getNickname()));
		}
		return new ResponseEntity<List<PostWithTag>>(postWithTagList,
				HttpStatus.OK);
	}

	// show blog
	@ApiOperation(value = "블로그 메인에서 모든 포스트의 개수 반환. (ex.  { seq_user:0 , seq_blog:1 })", response = List.class)
	@GetMapping(value = "/blog/{seq_blog}")
	public ResponseEntity<Integer> selectPostCntByBlog(@PathVariable int seq_blog) throws Exception {
		logger.debug("selectPostCntByBlog - 호출");
		int seq_user = jwtService.getSeq();
		return new ResponseEntity<Integer>(postService.selectPostCntByBlog(seq_user, seq_blog), HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "블로그 메인에서 한 페이지의 포스트 반환. (ex. { seq_user:1 , seq_blog:1, offset:0, limit:6, tag:['python','c++']  } )", response = List.class)
	@PostMapping(value = "/blog")
	public ResponseEntity<List<PostWithTag>> selectPostByBlog(@RequestBody Map<String, Object> params) throws Exception {
		logger.debug("selectPostByBlog - 호출");
		int seq_user = jwtService.getSeq();
		
		List<Post> postList= postService.selectPostByBlog(seq_user, (int) params.get("seq_blog"),
				(int) params.get("offset"), (int) params.get("limit"), (List<String>) params.get("tag"));
		System.out.println(postList.size());
		List<PostWithTag> postWithTagList = new ArrayList<PostWithTag>();
		for(int i=0,size=postList.size();i<size;i++) {
			int seq = postList.get(i).getSeq();
			List<PostTag> tags = postTagService.selectAllPostTag(seq);
			int comment_count = postCommentService.selectPostCommentCnt(seq);
			postWithTagList.add(new PostWithTag(postList.get(i),tags,comment_count));
		}
		return new ResponseEntity<List<PostWithTag>>(postWithTagList,
				HttpStatus.OK);
	}

}
