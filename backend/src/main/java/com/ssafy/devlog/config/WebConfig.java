package com.ssafy.devlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.devlog.interceptor.JwtInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
            "/**"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor())
						.addPathPatterns("/**")
						.excludePathPatterns(EXCLUDE_PATHS);
    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//      // 모든 uri에 대해 http://localhost:18080, http://localhost:8180 도메인은 접근을 허용한다.
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080",
//                		"http://localhost:8080/**",
//                		"http://localhost:8080/*",
//                		"http://i3a402.p.ssafy.io");
//
//    }
//    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}