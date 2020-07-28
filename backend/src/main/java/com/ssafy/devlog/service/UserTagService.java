package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.UserTag;

public interface UserTagService {
	public List<UserTag> selectAllUserTag();
	public List<UserTag> selectUserTagByUser(int seq_user);
	public UserTag selectUserTagByUserAndTag(UserTag usertag);
	public int insertUserTag(UserTag usertag);
	public int updateUserTag(UserTag usertag);
	public int deleteUserTag(int seq);
}
