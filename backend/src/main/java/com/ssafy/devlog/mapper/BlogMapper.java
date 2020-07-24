package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.Blog;

@Mapper
public interface BlogMapper {
	public List<Blog> selectAllBlog();
	public Blog selectBlogByUser(int seq);
	public int insertBlog(Blog blog);
	public int updateBlog(Blog blog);
	public int deleteBlog(int seq);
}
