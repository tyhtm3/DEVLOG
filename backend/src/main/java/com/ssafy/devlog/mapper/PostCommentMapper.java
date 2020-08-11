package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PostComment;
import com.ssafy.devlog.dto.PostCommentUser;

@Mapper
public interface PostCommentMapper {

	public int selectPostCommentCnt(int seq_post);
	public List<PostCommentUser> selectAllPostComment(int seq_post);
	public List<PostComment> selectAllPostCommentByNeighbor(@Param("seq_user") int seq_user, @Param("seq_blog") int seq_blog);
	public int insertPostComment(PostComment postComment);
	public int updatePostComment(PostComment postComment);
	public int deletePostComment(int seq);
	
}
