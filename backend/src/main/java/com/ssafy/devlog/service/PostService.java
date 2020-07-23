package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.Post;

public interface PostService {

	public List<Post> selectAllPost();
	public List<Post> selectAllPostByNeighbor(int seq_user);
	public List<Post> selectAllPostByTag(List<String> tag);

	public List<Post> selectAllPostByBlog(int seq_blog);
	public List<Post> selectAllPostByBlogByTag(int seq_blog, List<String> tag);
	
	public Post selectPost(int seq);
	public int insertPost(Post post);
	public int updatePost(Post post);
	public int deletePost(int seq);
	
}
