package com.ssafy.devlog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PostLike;

@Mapper
public interface PostLikeMapper {

	public PostLike selectPostLikeByUser(@Param("seq_post") int seq_post, @Param("seq_user") int seq_user);
	public int selectPostLikeCount(int seq_post);
	public int insertPostLike(PostLike postLike);
	public int deletePostLike(int seq);
	public int updatePostLikeCount(@Param("seq")int seq, @Param("count")int count);
}
