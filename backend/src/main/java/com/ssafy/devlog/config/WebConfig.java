package com.ssafy.devlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}