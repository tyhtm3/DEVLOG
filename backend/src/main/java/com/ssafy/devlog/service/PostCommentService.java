package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PostComment;
import com.ssafy.devlog.dto.PostCommentUser;

public interface PostCommentService {
	
	public int selectPostCommentCnt(int seq_post);
	public List<PostCommentUser> selectAllPostComment(int seq_post);
	public List<PostComment> selectAllPostCommentByNeighbor(int seq_user, int seq_blog);
	public int insertPostComment(PostComment postComment);
	public int updatePostComment(PostComment postComment);
	public int deletePostComment(int seq);
}
