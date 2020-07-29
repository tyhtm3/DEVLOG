package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.UserStack;

public interface UserStackService {
	
	public List<UserStack> selectAllUserStack(int seq_user);
	public UserStack selectUserStackByUserAndStack(UserStack userStack);
	public int insertUserStack(UserStack userStack);
	public int deleteUserStack(int seq);
}
