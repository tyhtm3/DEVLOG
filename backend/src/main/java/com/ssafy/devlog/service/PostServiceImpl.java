package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper postMapper;
	
	@Override
	public List<Post> selectAllPost(){
		return postMapper.selectAllPost();
	}
	@Override
	public List<Post> selectAllPostByNeighbor(int seq_user){
		return postMapper.selectAllPostByNeighbor(seq_user);
	}
	@Override
	public List<Post> selectAllPostByTag(List<String> tag){
		return postMapper.selectAllPostByTag(tag);
	}
	
	@Override
	public List<Post> selectAllPostByBlog(int seq_blog){
		return postMapper.selectAllPostByBlog(seq_blog);
	}
	
	@Override
	public List<Post> selectAllPostByBlogByTag(int seq_blog, List<String> tag){
		return postMapper.selectAllPostByBlogByTag(seq_blog, tag);
	}
	
	@Override
	public Post selectPost(int seq){
		return postMapper.selectPost(seq);
	}
	
	@Override
	public int insertPost(int seq_blog, Post post){
		return postMapper.insertPost(seq_blog, post);
	}
	
	@Override
	public int updatePost(Post post){
		return postMapper.updatePost(post);
	}
	@Override
	public int deletePost(int seq){
		return postMapper.deletePost(seq);
	}

}
