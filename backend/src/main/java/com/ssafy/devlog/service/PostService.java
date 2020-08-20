package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.Post;

public interface PostService {

	public List<Post> selectPostByFeed(int seq_user,int disclosure,int offset,int limit,List<String> tag);
	public List<Post> selectPostByBlog(int seq_user,int seq_blog,int offset,int limit,List<String> tag);
	public int selectPostCntByBlog(int seq_user,int seq_blog);
	
	public Post selectPost(int seq);
	public List<Post> selectDraftPost(int seq_blog);
	public String selectPostTitle(int seq);
	public int insertPost(Post post);
	public int insertPostBasic(Post post);
	public int updatePost(Post post);
	public int updatePostBasic(Post post);
	public int deletePost(int seq);
	
}
