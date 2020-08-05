package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PostTag;

public interface PostTagService {
	
	public List<PostTag> selectAllPostTag(int seq_post);
	public int insertPostTag(int seq_post, List<String> tag);
	public int deletePostTag(int seq_post);
}
