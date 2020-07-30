package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PostComment;

public interface PostCommentService {
	
	public int selectPostCommentCnt(int seq_post);
	public List<PostComment> selectAllPostComment(int seq_post);
	public int insertPostComment(PostComment postComment);
	public int updatePostComment(PostComment postComment);
	public int deletePostComment(int seq);
}
