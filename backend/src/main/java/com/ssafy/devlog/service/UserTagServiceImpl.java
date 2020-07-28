package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.UserTag;
import com.ssafy.devlog.mapper.UserTagMapper;

@Service
public class UserTagServiceImpl implements UserTagService {
	
	@Autowired
	private UserTagMapper userTagMapper;
	
	@Override
	public List<UserTag> selectAllUserTag() {
		return userTagMapper.selectAllUserTag();
	}

	@Override
	public List<UserTag> selectUserTagByUser(int seq_user) {
		return userTagMapper.selectUserTagByUser(seq_user);
	}

	@Override
	public UserTag selectUserTagByUserAndTag(UserTag usertag) {
		return userTagMapper.selectUserTagByUserAndTag(usertag);
	}
	
	@Override
	public int insertUserTag(UserTag userTag) {
		return userTagMapper.insertUserTag(userTag);
	}

	@Override
	public int updateUserTag(UserTag userTag) {
		return userTagMapper.updateUserTag(userTag);
	}

	@Override
	public int deleteUserTag(int seq) {
		return userTagMapper.deleteUserTag(seq);
	}


}
