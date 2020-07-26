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
	public List<Post> selectAllPost(int seq_user){
		return postMapper.selectAllPost(seq_user);
	}
	@Override
	public List<Post> selectAllPostByNeighbor(int seq_user){
		return postMapper.selectAllPostByNeighbor(seq_user);
	}
	@Override
	public List<Post> selectAllPostByTag(int seq_user, List<String> tag){
		return postMapper.selectAllPostByTag(seq_user, tag);
	}
	
	@Override
	public List<Post> selectAllPostByBlog(int seq_user, int seq_blog){
		return postMapper.selectAllPostByBlog(seq_user, seq_blog);
	}
	
	@Override
	public List<Post> selectAllPostByBlogByTag(int seq_user, int seq_blog, List<String> tag){
		return postMapper.selectAllPostByBlogByTag(seq_user, seq_blog, tag);
	}
	
	@Override
	public Post selectPost(int seq){
		return postMapper.selectPost(seq);
	}
	
	@Override
	public int insertPost(Post post){
		return postMapper.insertPost(post);
	}
	
	@Override
	public int insertPostBasic(Post post){
		return postMapper.insertPostBasic(post);
	}
	
	@Override
	public int updatePost(Post post){
		return postMapper.updatePost(post);
	}
	
	@Override
	public int updatePostBasic(Post post) {
		return postMapper.updatePostBasic(post);
	}
	
	@Override
	public int deletePost(int seq){
		return postMapper.deletePost(seq);
	}

}
