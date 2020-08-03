package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.UserStack;
import com.ssafy.devlog.mapper.UserStackMapper;

@Service
public class UserStackServiceImpl implements UserStackService {

	@Autowired
	private UserStackMapper userStackMapper;

	@Override
	public List<UserStack> selectAllUserStack(int seq_user) {
		return userStackMapper.selectAllUserStack(seq_user);
	}
	@Override
	public List<UserStack> selectUserImageStack(int seq_user) {
		return userStackMapper.selectUserImageStack(seq_user);
	}

	@Override
	public List<UserStack> selectUserTextStack(int seq_user) {
		// TODO Auto-generated method stub
		return userStackMapper.selectUserTextStack(seq_user);
	}
	@Override
	public int insertUserStack(UserStack userStack) {
		return userStackMapper.insertUserStack(userStack);
	}

	@Override
	public int deleteUserStack(int seq) {
		return userStackMapper.deleteUserStack(seq);
	}

	@Override
	public UserStack selectUserStackByUserAndStack(UserStack userStack) {
		return userStackMapper.selectUserStackByUserAndStack(userStack);
	}

	@Override
	public int updateUserStackImg(UserStack userStack) {
		return userStackMapper.updateUserStackImg(userStack);
	}

	

}
