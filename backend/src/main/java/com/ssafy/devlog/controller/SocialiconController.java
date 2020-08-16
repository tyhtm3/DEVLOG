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

import com.ssafy.devlog.dto.Socialicon;
import com.ssafy.devlog.dto.UserStack;
import com.ssafy.devlog.dto.UserTag;
import com.ssafy.devlog.service.JwtService;
import com.ssafy.devlog.service.SocialiconService;
import com.ssafy.devlog.service.UserStackService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/socialicon")
public class SocialiconController {

	private static final Logger logger = LoggerFactory.getLogger(StackController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private SocialiconService socialiconService;

	@ApiOperation(value = "모든 소셜 아이콘을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Socialicon>> selectAllSocialicon() throws Exception {
		logger.debug("selectAllSocialicon - 호출");
		return new ResponseEntity<List<Socialicon>>(socialiconService.selectAllSocialicon(), HttpStatus.OK);
	}
	
	
}
