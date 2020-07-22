package com.ssafy.devlog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Portfolio;

public interface PortfolioService {
	
	public List<Portfolio> selectAllPortfolioByBlog(int seq_blog);
	public List<Portfolio> selectAllPortfolioByBlogByTag(@Param("seq_blog") int seq_blog, @Param("tag") List<String> tag);
	
	public Portfolio selectPortfolio(int seq);
	public int insertPortfolio(@Param("seq_blog") int seq_blog, @Param("portfolio") Portfolio portfolio);
	public int updatePortfolio(Portfolio portfolio);
	public int deletePortfolio(int seq);
	
}
