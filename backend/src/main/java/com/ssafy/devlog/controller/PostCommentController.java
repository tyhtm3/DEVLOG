package com.ssafy.devlog.controller;

import java.util.ArrayList;
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
import com.ssafy.devlog.dto.PostCommentUser;
import com.ssafy.devlog.dto.User;
import com.ssafy.devlog.service.JwtService;
import com.ssafy.devlog.service.PostCommentService;
import com.ssafy.devlog.service.PostService;
import com.ssafy.devlog.service.UserService;

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
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private JwtService jwtService;

	@ApiOperation(value = "포스트 및 프로젝트의 seq로 댓글 개수를 반환한다.", response = List.class)
	@GetMapping(value = "/count/{seq_post}")
	public ResponseEntity<Integer> selectPostCommentCnt(@PathVariable int seq_post) throws Exception {
		logger.debug("selectPostCommentCnt - 호출");
		return new ResponseEntity<Integer>(postCommentService.selectPostCommentCnt(seq_post), HttpStatus.OK);
	}

	@ApiOperation(value = "포스트 및 프로젝트의 seq로 모든 댓글을 반환한다.", response = List.class)
	@GetMapping(value = "/{seq_post}")
	public ResponseEntity<List<PostCommentUser>> selectAllPostComment(@PathVariable int seq_post) throws Exception {
		logger.debug("selectAllPostComment - 호출");
		return new ResponseEntity<List<PostCommentUser>>(postCommentService.selectAllPostComment(seq_post),
				HttpStatus.OK);
	}

	@ApiOperation(value = "특정 이웃이 남긴 댓글을 전부 불러온다. // seq_user : 이웃의 seq ", response = List.class)
	@GetMapping(value = "/neighbor/{seq_user}")
	public ResponseEntity<List<PostCommentUser>> selectAllPostCommentByNeighbor(@PathVariable int seq_user)
			throws Exception {
		logger.debug("selectAllPostCommentByNeighbor - 호출");
		int seq_blog = jwtService.getSeq();
		User user = userService.selectUserBySeq(seq_user);
		List<PostCommentUser> postCommentUserList = new ArrayList<PostCommentUser>();
		List<PostComment> postCommentList = postCommentService.selectAllPostCommentByNeighbor(seq_user, seq_blog);
		for (int i = 0; i < postCommentList.size(); i++) {
			postCommentUserList.add(new PostCommentUser(postCommentList.get(i), user));
			int seq_post = postCommentList.get(i).getSeq_post();
			postCommentUserList.get(i).setTitle(postService.selectPostTitle(seq_post));
		}
		return new ResponseEntity<List<PostCommentUser>>(postCommentUserList, HttpStatus.OK);
	}

	@ApiOperation(value = "내가 특정 이웃에게 남긴 댓글을 전부 불러온다. // seq_neighbor : 이웃의 seq ", response = List.class)
	@GetMapping(value = "/neighbor/me/{seq_neighbor}")
	public ResponseEntity<List<PostCommentUser>> selectAllPostCommentByMe(@PathVariable int seq_neighbor)
			throws Exception {
		logger.debug("selectAllPostCommentByNeighbor - 호출");
		int seq_user = jwtService.getSeq();
		int seq_blog = seq_neighbor;
		User user = userService.selectUserBySeq(seq_user);
		List<PostCommentUser> postCommentUserList = new ArrayList<PostCommentUser>();
		List<PostComment> postCommentList = postCommentService.selectAllPostCommentByNeighbor(seq_user, seq_blog);
		for (int i = 0; i < postCommentList.size(); i++) {
			postCommentUserList.add(new PostCommentUser(postCommentList.get(i), user));
			int seq_post = postCommentList.get(i).getSeq_post();
			postCommentUserList.get(i).setTitle(postService.selectPostTitle(seq_post));
		}
		return new ResponseEntity<List<PostCommentUser>>(postCommentUserList, HttpStatus.OK);
	}

	@ApiOperation(value = "포스트 및 프로젝트에 댓글을 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertPostComment(@RequestBody PostComment postComment) throws Exception {
		logger.debug("inserPostComment - 호출");
		postComment.setSeq_user(jwtService.getSeq());
		if (postCommentService.insertPostComment(postComment) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "포스트 및 프로젝트의 댓글을 수정한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updatePostComment(@RequestBody PostComment postComment) throws Exception {
		logger.debug("updatePostComment - 호출");
		postComment.setSeq_user(jwtService.getSeq());
		if (postCommentService.updatePostComment(postComment) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "포스트 및 프로젝트의 댓글을 삭제한다.", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deletePostComment(@PathVariable int seq) throws Exception {
		logger.debug("deletePostComment - 호출");
		if (postCommentService.deletePostComment(seq) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
