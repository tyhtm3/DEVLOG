package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.BlogTag;

@Mapper
public interface BlogTagMapper {
	public List<BlogTag> selectAllBlogTag();
	public List<BlogTag> selectBlogTagByBlog(int seq_blog);
	public BlogTag selectBlogTagByBlogAndTag(BlogTag blogTag);
	public int insertBlogTag(BlogTag blogtag);
	public int updateBlogTag(BlogTag blogtag);
	public int deleteBlogTag(int seq);
}
