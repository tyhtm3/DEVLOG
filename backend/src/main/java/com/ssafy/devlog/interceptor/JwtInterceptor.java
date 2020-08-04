package com.ssafy.devlog.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.devlog.common.UnauthorizedException;
import com.ssafy.devlog.service.JwtService;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter{
	private static final String HEADER_AUTH = "Authorization";
	
	@Resource
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("before token");
		final String token = request.getHeader(HEADER_AUTH);
		System.out.println("after token");
//		if(token != null && jwtService.isUsable(token)){
//			return true;
//		}else{
//			throw new UnauthorizedException();
//		}
		return super.preHandle(request,response,handler);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 실행 후
		System.out.println("=====filter end======");
		super.postHandle(request, response, handler, modelAndView);
	}
}
