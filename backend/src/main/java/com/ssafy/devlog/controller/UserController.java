package com.ssafy.devlog.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.devlog.dto.Blog;
import com.ssafy.devlog.dto.User;
import com.ssafy.devlog.service.BlogService;
import com.ssafy.devlog.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;

	@ApiOperation(value = "모든 회원을 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<User>> selectAllUser() throws Exception {
		logger.debug("selectAllUser - 호출");
		return new ResponseEntity<List<User>>(userService.selectAllUser(), HttpStatus.OK);
	}

	@ApiOperation(value = "로그인 시 id, password를 입력받아 일치여부를 확인한다.", response = List.class)
	@PostMapping("/login")
	public ResponseEntity<User> selectUserById(@RequestBody User user) throws Exception {
		logger.debug("selectUserById - 호출");
		String id = user.getId();
		String pwd = user.getPassword();
		user = userService.selectUserById(id);
		if (user == null || !user.getPassword().equals(pwd)) {
			return null;
		} else
			return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 회원의 정보를 반환한다.", response = List.class)
	@GetMapping("{seq}")
	public ResponseEntity<User> selectUserBySeq(@PathVariable int seq) throws Exception {
		logger.debug("selectUserBySeq - 호출");
		return new ResponseEntity<User>(userService.selectUserBySeq(seq), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 회원을 생성하며 동시에 개인 블로그도 같이 생성해준다", response = List.class)
	@PostMapping
	public ResponseEntity<String> insertUser(@RequestBody User user) throws Exception {
		logger.debug("insertUser - 호출");
		if (userService.selectUserById(user.getId()) != null) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		if (userService.insertUser(user) == 1) {
			Blog blog = new Blog();
			user = userService.selectUserById(user.getId());
			blog.setSeq(user.getSeq());
			blog.setBlog_name(user.getId() + "님의 블로그");
			blog.setBlog_detail("블로그 소개를 입력해주세요");
			if (blogService.insertBlog(blog) == 1)
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			else
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원정보를 수정한다", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		logger.debug("updateUser - 호출");
		if (userService.updateUser(user) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원을 삭제한다.", response = List.class)
	@DeleteMapping("{seq}")
	public ResponseEntity<String> deleteBlog(@PathVariable int seq) throws Exception {
		logger.debug("deleteUser - 호출");
		if (userService.deleteUser(seq) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "프로필 이미지 파일을 업로드 한다.")
	@PostMapping("upload")
	public String doFileUpload(@RequestParam("upload_file") MultipartFile uploadfile, HttpServletRequest request) {
		try {
			// 업로드 파일 이름에 날짜로 해싱
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date nowdate = new Date();
			String dateString = formatter.format(nowdate);

			// 웹서비스 경로 지정
			// String root_path = request.getSession().getServletContext().getRealPath("/");
			String root_path = ("/home/ubuntu");
			String attach_path = "profile_img/";
			String filename = dateString + "_" + uploadfile.getOriginalFilename();

			// System.out.println(root_path+attach_path+filename);

			FileOutputStream fos = new FileOutputStream(root_path + attach_path + filename);
			// 파일 저장할 경로 + 파일명을 파라미터로 넣고 fileOutputStream 객체 생성하고
			InputStream is = uploadfile.getInputStream();
			// file로 부터 inputStream을 가져온다.

			int readCount = 0;
			byte[] buffer = new byte[2048];
			// 파일을 읽을 크기 만큼의 buffer를 생성하고
			// ( 보통 1024, 2048, 4096, 8192 와 같이 배수 형식으로 버퍼의 크기를 잡는 것이 일반적이다.)

			while ((readCount = is.read(buffer)) != -1) {
				// 파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
				fos.write(buffer, 0, readCount);
				// 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
			}

			return root_path + attach_path + filename;

		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
}
