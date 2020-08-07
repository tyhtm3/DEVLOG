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
@RequestMapping("/api/stack")
public class StackController {

	private static final Logger logger = LoggerFactory.getLogger(StackController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserStackService userStackService;

	@ApiOperation(value = "유저의 모든 기술스택을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserStack>> selectAllUserStack() throws Exception {
		logger.debug("selectAllUserStack - 호출");
		return new ResponseEntity<List<UserStack>>(userStackService.selectAllUserStack(0), HttpStatus.OK);
	}
	
	
}
