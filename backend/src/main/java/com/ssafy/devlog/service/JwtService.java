package com.ssafy.devlog.service;

import java.util.Map;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	int get(String key);
	int getSeq();
	boolean isUsable(String jwt);
	
}
