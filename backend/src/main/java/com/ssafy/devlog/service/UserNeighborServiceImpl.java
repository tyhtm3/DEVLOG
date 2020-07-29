package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.UserNeighbor;
import com.ssafy.devlog.mapper.UserNeighborMapper;

@Service
public class UserNeighborServiceImpl implements UserNeighborService {

	@Autowired
	private UserNeighborMapper userNeighborMapper;

	@Override
	public List<UserNeighbor> selectAllUserNeighbor(int seq_user) {
		return userNeighborMapper.selectAllUserNeighbor(seq_user);
	}

	@Override
	public int insertUserNeighbor(UserNeighbor userNeighbor) {
		return userNeighborMapper.insertUserNeighbor(userNeighbor);
	}

	@Override
	public int deleteUserNeighbor(int seq) {
		return userNeighborMapper.deleteUserNeighbor(seq);
	}


}
