package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.PostTag;

@Mapper
public interface PostTagMapper {

	public List<PostTag> selectAllPostTag(int seq_post);
	public int insertPostTag(PostTag postTag);
	public int deletePostTag(int seq);
	
}
