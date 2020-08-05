package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PostTag;
import com.ssafy.devlog.mapper.PostTagMapper;

@Service
public class PostTagServiceImpl implements PostTagService {
	
	@Autowired
	private PostTagMapper postTagMapper;
	

	@Override
	public List<PostTag> selectAllPostTag(int seq_post){
		return postTagMapper.selectAllPostTag(seq_post);
	}
	
	@Override
	public int insertPostTag(int seq_post, List<String> tag) {
		return postTagMapper.insertPostTag(seq_post, tag);
	}
	
	@Override
	public int deletePostTag(int seq_post) {
		return postTagMapper.deletePostTag(seq_post);
	}

}
