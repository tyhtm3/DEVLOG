package com.ssafy.devlog.service;

import com.ssafy.devlog.dto.PostLike;

public interface PostLikeService {
	
	public PostLike selectPostLikeByUser(int seq_post,int seq_user);
	public int selectPostLikeCount(int seq_post);
	public int insertPostLike(PostLike postLike);
	public int deletePostLike(int seq);
	public int updatePostLikeCount(int seq, int count);
}
