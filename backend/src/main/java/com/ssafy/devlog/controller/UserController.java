package com.ssafy.devlog.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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

	@ApiOperation(value = "로그인 시 id, password를 입력받아 일치여부를 확인한다. // 아이디없음 : 404 , 비밀번호 틀림 : 401", response = List.class)
	@PostMapping("/login")
	public ResponseEntity<User> selectUserById(@RequestBody User user) throws Exception {
		logger.debug("selectUserById - 호출");
		String id = user.getId();
		String pwd = user.getPassword();
		user = userService.selectUserById(id);
		if (user == null) {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		else if (!user.getPassword().equals(pwd)) {
			user =null;
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
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
			return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
		}
		if (userService.insertUser(user) == 1) {
			Blog blog = new Blog();
			user = userService.selectUserById(user.getId());
			blog.setSeq(user.getSeq());
			blog.setBlog_name(user.getId() + "님의 블로그");
			blog.setBlog_detail("블로그 소개를 입력해주세요");
			if (blogService.insertBlog(blog) == 1) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
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
	public String doFileUpload(@RequestParam("upload_file") MultipartFile uploadfile) {
		try {
			// 업로드 파일 이름에 날짜로 해싱
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			Date nowdate = new Date();
			String dateString = formatter.format(nowdate);

			// 웹서비스 경로 지정
			// String root_path = request.getSession().getServletContext().getRealPath("/");
			String root_path = ("/home/ubuntu/");
			String attach_path = "images/";
			String filename = dateString + "_" + uploadfile.getOriginalFilename();

			 System.out.println(root_path+attach_path+filename);

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
			ex.printStackTrace();
			return FAIL;
		}
	}
	
	
	@ApiOperation(value = "네이버 로그인")
	@GetMapping("naver")
	public void naver(@RequestParam(value = "code") String code,@RequestParam(value = "state") String state, HttpServletResponse response ) throws Exception {
	
	
		String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=RSKBTL31UOSpdlckpmTt";
        apiURL += "&client_secret=MK0P6WjQXn" ;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        
        try {
        	
        	//로그인이 정상적으로 된 상황이므로 code 와 state, secret pw로 네이버 apiURL을 통해 토큰을 요청한다.
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { 													
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  																	
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            
            if(responseCode==200) { 
            	
            	//  정상적으로 토큰을 가져오면 Gson 으로 JSON 파일을 파싱해준다.
                int id;
                String nickName, email, tmp;
                JsonParser parser = new JsonParser();
                JsonElement accessElement = parser.parse(res.toString());
                access_token = accessElement.getAsJsonObject().get("access_token").getAsString();
           
                // 파싱한 access_token 값으로 네이버에 유저 정보를 요청. 이 함수의 return 값은 id, email, nickname 등 유저 정보들과 상태 코드 등이다.
                tmp = getUserInfo(access_token);
                
                JsonElement userInfoElement = parser.parse(tmp);
                id = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsInt();
                nickName = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("nickname").getAsString();
                email = userInfoElement.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
     
                // 마지막으로 이러한 유저 정보들을 JWT 로 만들어준다. 
                access_token = createJWTToken(id, nickName, email);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        response.sendRedirect("http://localhost:8080/#/dashboard-ju/"+access_token);
	}
	
	private String createJWTToken(int id, String nickname, String email) {
        String token = null;
        DecodedJWT jwt = null;

        try {
            Long EXPIRATION_TIME = 1000L * 60L * 10L;
            Date issuedAt = new Date();
            Date notBefore = new Date(issuedAt.getTime());
            Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);

            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withIssuer("auth0") 		//발행자
                    .withSubject(nickname)		//!닉네임
                    .withAudience("devlog") 	//토큰 대상자
                    .withClaim("id", id) 		//!아이디
                    .withClaim("email", email)	//!이메일
                    .withNotBefore(notBefore) 	//토큰의 활성 날짜
                    .withExpiresAt(expiresAt) 	//만료시간
                    .sign(algorithm);
        } catch (Exception e) {
            System.err.println("err: " + e);
        }
        return token;
    }
	
	
	private String getUserInfo(String access_token) {
        String header = "Bearer " + access_token; // Bearer 다음에 공백 추가해야함
        try {
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", header);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            return res.toString();
        } catch (Exception e) {
            System.err.println(e);
            return "Err";
        }
    }
	
}
