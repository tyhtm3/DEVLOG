package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.PostComment;

@Mapper
public interface PostCommentMapper {

	public List<PostComment> selectAllPostComment(int seq_post);
	public int insertPostComment(PostComment postComment);
	public int updatePostComment(PostComment postComment);
	public int deletePostComment(int seq);
	
}
