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

import com.ssafy.devlog.dto.UserTag;
import com.ssafy.devlog.service.UserTagService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/usertag")
public class UserTagController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTagController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserTagService userTagService;
	
	@ApiOperation(value = "모든 태그를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserTag>> selectAllUserTag() throws Exception {
		logger.debug("selectAllTag - 호출");
		return new ResponseEntity<List<UserTag>>(userTagService.selectAllUserTag(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 유저의 태그를 반환한다.", response = List.class)
	@GetMapping("{user_seq}")
	public ResponseEntity<UserTag> selectUserTagByUser(@PathVariable int user_seq) throws Exception {
		logger.debug("selectUserTagByUser - 호출");
		return new ResponseEntity<UserTag>(userTagService.selectUserTagByUser(user_seq), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 태그를 생성한다", response = List.class)
	@PostMapping
	public ResponseEntity<String> insertUserTag(@RequestBody UserTag userTag) throws Exception {
		logger.debug("insertUserTag - 호출");
		if(userTagService.insertUserTag(userTag)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "특정 태그를 삭제한다.", response = List.class)
	@DeleteMapping("{seq}")
	public ResponseEntity<String> deleteUserTag(@PathVariable int seq) throws Exception {
		logger.debug("deleteUserTag - 호출");
		if(userTagService.deleteUserTag(seq)==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
