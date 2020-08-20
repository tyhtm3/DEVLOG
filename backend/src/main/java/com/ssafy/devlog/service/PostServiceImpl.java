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
	public List<Post> selectPostByFeed(int seq_user,int disclosure,int offset,int limit,List<String> tag){
		return postMapper.selectPostByFeed(seq_user, disclosure, offset, limit, tag);
	}
	
	@Override
	public List<Post> selectPostByBlog(int seq_user,int seq_blog,int offset,int limit,List<String> tag){
		return postMapper.selectPostByBlog(seq_user, seq_blog, offset, limit, tag);
	}
	
	@Override
	public int selectPostCntByBlog(int seq_user,int seq_blog) {
		return postMapper.selectPostCntByBlog(seq_user, seq_blog);
	}
	
	@Override
	public Post selectPost(int seq){
		return postMapper.selectPost(seq);
	}
	
	@Override
	public List<Post> selectDraftPost(int seq_blog){
		return postMapper.selectDraftPost(seq_blog);
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

	@Override
	public String selectPostTitle(int seq) {
		return postMapper.selectPostTitle(seq);
	}

}
