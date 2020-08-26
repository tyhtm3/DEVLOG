package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.User;
import com.ssafy.devlog.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> selectAllUser() {
		return userMapper.selectAllUser();
	}

	@Override
	public User selectUserBySeq(int seq) {
		return userMapper.selectUserBySeq(seq);
	}

	@Override
	public User selectUserById(String id) {
		return userMapper.selectUserById(id);
	}

	@Override
	public User selectUserBySocialId(String social_id) {
		return userMapper.selectUserBySocialId(social_id);
	}
	
	@Override
	public User selectUserByEmail(String email) {
		return userMapper.selectUserByEmail(email);
	}
	@Override
	public int insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(int seq) {
		return userMapper.deleteUser(seq);
	}

	@Override
	public String selectLastSeq() {
		return userMapper.selectLastSeq();
	}



}
