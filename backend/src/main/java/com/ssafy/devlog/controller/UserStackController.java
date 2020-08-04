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

import com.ssafy.devlog.dto.UserStack;
import com.ssafy.devlog.dto.UserTag;
import com.ssafy.devlog.service.JwtService;
import com.ssafy.devlog.service.UserStackService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/userstack")
public class UserStackController {

	private static final Logger logger = LoggerFactory.getLogger(UserStackController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserStackService userStackService;
	@Autowired
	private JwtService jwtService;

	@ApiOperation(value = "유저의 모든 기술스택을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserStack>> selectAllUserStack() throws Exception {
		logger.debug("selectAllUserStack - 호출");
		int seq_user = jwtService.getSeq();
		return new ResponseEntity<List<UserStack>>(userStackService.selectAllUserStack(seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 모든 이미지스택을 반환한다.", response = List.class)
	@GetMapping(value = "/image")
	public ResponseEntity<List<UserStack>> selectUserImageStack() throws Exception {
		logger.debug("selectUserImageStack - 호출");
		int seq_user = jwtService.getSeq();
		return new ResponseEntity<List<UserStack>>(userStackService.selectUserImageStack(seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 모든 텍스트스택을 반환한다.", response = List.class)
	@GetMapping(value = "/text")
	public ResponseEntity<List<UserStack>> selectUserTextStack() throws Exception {
		logger.debug("selectUserTextStack - 호출");
		int seq_user = jwtService.getSeq();
		return new ResponseEntity<List<UserStack>>(userStackService.selectUserTextStack(seq_user), HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저에 기술스택을 추가한다. //중복 입력시 401", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertUserStack(@RequestBody UserStack userStack) throws Exception {
		logger.debug("insertUserStack - 호출");
		userStack.setSeq_user(jwtService.getSeq());
		UserStack check = userStackService.selectUserStackByUserAndStack(userStack);
		if (check != null)
			return new ResponseEntity<String>(FAIL, HttpStatus.UNAUTHORIZED);
		else if (userStackService.insertUserStack(userStack) == 1) {
			//이미지 경로 추가
			//techstack 테이블에 해당 stack명이 있으면 이미지를 넣고 없으면 null
			userStackService.updateUserStackImg(userStack);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "유저의 기술스택을 삭제한다.", response = String.class)
	@DeleteMapping(value = "/{seq}")
	public ResponseEntity<String> deleteUserStack(@PathVariable int seq) throws Exception {
		logger.debug("deleteUserStack - 호출");
		if (userStackService.deleteUserStack(seq) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
