package com.ssafy.devlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PostLike;
import com.ssafy.devlog.mapper.PostLikeMapper;

@Service
public class PostLikeServiceImpl implements PostLikeService {
	
	@Autowired
	private PostLikeMapper postLikeMapper;
	
	@Override
	public PostLike selectPostLikeByUser(int seq_post,int seq_user) {
		return postLikeMapper.selectPostLikeByUser(seq_post, seq_user);
	}
	@Override
	public int selectPostLikeCount(int seq_post) {
		return postLikeMapper.selectPostLikeCount(seq_post);
	}
	@Override
	public int insertPostLike(PostLike postLike) {
		return postLikeMapper.insertPostLike(postLike);
	}
	@Override
	public int deletePostLike(int seq) {
		return postLikeMapper.deletePostLike(seq);
	}
	@Override
	public int updatePostLikeCount(int seq, int count) {
		return postLikeMapper.updatePostLikeCount(seq, count);
	}
}
