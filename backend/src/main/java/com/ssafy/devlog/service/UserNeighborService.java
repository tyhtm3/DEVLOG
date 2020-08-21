package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.UserNeighbor;

public interface UserNeighborService {
	
	public List<UserNeighbor> selectAllUserNeighbor(int seq_user);
	public List<UserNeighbor> selectAllUserNeighborMe(int seq_user);
	public int insertUserNeighbor(UserNeighbor userNeighbor);
	public int deleteUserNeighbor(UserNeighbor userNeighbor);
	public UserNeighbor checkUserNeighbor(int seq_user, int seq_neighbor);
	
}
