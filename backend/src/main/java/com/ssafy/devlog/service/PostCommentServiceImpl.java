package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PostComment;
import com.ssafy.devlog.dto.PostCommentUser;
import com.ssafy.devlog.mapper.PostCommentMapper;

@Service
public class PostCommentServiceImpl implements PostCommentService {
	
	@Autowired
	private PostCommentMapper postCommentMapper;
	

	@Override
	public int selectPostCommentCnt(int seq_post) {
		return postCommentMapper.selectPostCommentCnt(seq_post);
	}
	
	@Override
	public List<PostCommentUser> selectAllPostComment(int seq_post){
		return postCommentMapper.selectAllPostComment(seq_post);
	}
	
	@Override
	public int insertPostComment(PostComment postComment) {
		return postCommentMapper.insertPostComment(postComment);
	}

	@Override
	public int updatePostComment(PostComment postComment) {
		return postCommentMapper.updatePostComment(postComment);
	}

	@Override
	public int deletePostComment(int seq) {
		return postCommentMapper.deletePostComment(seq);
	}

	@Override
	public List<PostComment> selectAllPostCommentByNeighbor(int seq_user,int seq_blog) {
		return postCommentMapper.selectAllPostCommentByNeighbor(seq_user, seq_blog);
	}

}
