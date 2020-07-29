package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.UserTag;

@Mapper
public interface UserTagMapper {
	public List<UserTag> selectAllUserTag();
	public List<UserTag> selectUserTagByUser(int seq_user);
	public UserTag selectUserTagByUserAndTag(UserTag usertag);
	public int insertUserTag(UserTag usertag);
	public int updateUserTag(UserTag usertag);
	public int deleteUserTag(int seq);
}
