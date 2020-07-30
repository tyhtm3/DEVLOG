package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.BlogTag;

public interface BlogTagService {
	public List<BlogTag> selectAllBlogTag();
	public List<BlogTag> selectBlogTagByBlog(int seq_blog);
	public BlogTag selectBlogTagByBlogAndTag(BlogTag blogTag);
	public int insertBlogTag(BlogTag blogTag);
	public int updateBlogTag(BlogTag blogTag);
	public int deleteBlogTag(int seq);
}