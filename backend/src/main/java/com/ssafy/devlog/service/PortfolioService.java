package com.ssafy.devlog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Portfolio;

public interface PortfolioService {
	
	public List<Portfolio> selectPortfolioByBlog(int seq_user,int seq_blog,int offset,int limit);


	public Portfolio selectPortfolio(int seq);
	public Portfolio selectPortfolioByRep(int seq_blog);
	public int insertPost(Portfolio portfolio);
	public int insertPostPortfolio(Portfolio portfolio);
	public int updatePost (Portfolio portfolio);
	public int updatePostPortfolio(Portfolio portfolio);
	public int updatePortfolioGeneral(int seq_blog);
	public int updatePortfolioRepresentation(int seq);
	public int deletePortfolio(int seq);
	
}
