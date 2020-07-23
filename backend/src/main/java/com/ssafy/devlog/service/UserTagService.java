package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.UserTag;

public interface UserTagService {
	public List<UserTag> selectAllUserTag();
	public UserTag selectUserTagByUser(int seq_user);
	public int insertUserTag(UserTag usertag);
	public int updateUserTag(UserTag usertag);
	public int deleteUserTag(int seq);
}
