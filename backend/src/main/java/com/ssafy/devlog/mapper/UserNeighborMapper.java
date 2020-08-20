package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.UserNeighbor;
import com.ssafy.devlog.dto.UserStack;

@Mapper
public interface UserNeighborMapper {

	public List<UserNeighbor> selectAllUserNeighbor(int seq_user);
	public List<UserNeighbor> selectAllUserNeighborMe(int seq_user);
	public int insertUserNeighbor(UserNeighbor userNeighbor);
	public int deleteUserNeighbor(UserNeighbor userNeighbor);
	public UserNeighbor checkUserNeighbor(@Param("seq_user")int seq_user,@Param("seq_neighbor")int seq_neighbor);
	
}
