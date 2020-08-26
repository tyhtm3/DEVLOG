package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.User;

@Mapper
public interface UserMapper {
	public List<User> selectAllUser();
	public User selectUserBySeq(int seq);
	public User selectUserById(String id);
	public User selectUserByEmail(String email);
	public User selectUserBySocialId(String social_id);
	public int insertUser(User user);
	public int updateUser(User user);
	public int deleteUser(int seq);
	public String selectLastSeq();
}
