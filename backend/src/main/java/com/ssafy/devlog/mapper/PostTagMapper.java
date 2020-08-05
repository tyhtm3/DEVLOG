package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PostTag;

@Mapper
public interface PostTagMapper {

	public List<PostTag> selectAllPostTag(int seq_post);
	public int insertPostTag(@Param("seq_post")int seq_post, @Param("tag")List<String> tag);
	public int deletePostTag(int seq_post);
	
}
