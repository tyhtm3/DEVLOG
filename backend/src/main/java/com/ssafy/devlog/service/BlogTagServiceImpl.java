package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.BlogTag;
import com.ssafy.devlog.mapper.BlogTagMapper;

@Service
public class BlogTagServiceImpl implements BlogTagService {
	
	@Autowired
	private BlogTagMapper blogTagMapper;
	
	@Override
	public List<BlogTag> selectAllBlogTag() {
		return blogTagMapper.selectAllBlogTag();
	}

	@Override
	public BlogTag selectBlogTagByBlogAndTag(BlogTag blogTag) {
		return blogTagMapper.selectBlogTagByBlogAndTag(blogTag);
	}
	
	@Override
	public List<BlogTag> selectBlogTagByBlog(int seq_blog) {
		return blogTagMapper.selectBlogTagByBlog(seq_blog);
	}

	@Override
	public int insertBlogTag(BlogTag blogTag) {
		return blogTagMapper.insertBlogTag(blogTag);
	}

	@Override
	public int updateBlogTag(BlogTag blogTag) {
		return blogTagMapper.updateBlogTag(blogTag);
	}

	@Override
	public int deleteBlogTag(int seq) {
		return blogTagMapper.deleteBlogTag(seq);
	}


}
