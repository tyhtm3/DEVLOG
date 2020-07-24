package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.Blog;

public interface BlogService {
	public List<Blog> selectAllBlog();
	public Blog selectBlogByUser(int seq);
	public int insertBlog(Blog blog);
	public int updateBlog(Blog blog);
	public int deleteBlog(int seq);
}
