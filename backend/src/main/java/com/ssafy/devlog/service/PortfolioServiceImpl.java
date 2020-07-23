package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.dto.Portfolio;
import com.ssafy.devlog.mapper.PortfolioMapper;

@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Override
	public List<Portfolio> selectAllPortfolioByBlog(int seq_blog){
		return portfolioMapper.selectAllPortfolioByBlog(seq_blog);
	}
	
	@Override
	public List<Portfolio> selectAllPortfolioByBlogByTag(int seq_blog, List<String> tag){
		return portfolioMapper.selectAllPortfolioByBlogByTag(seq_blog, tag);
	}
	
	@Override
	public Portfolio selectPortfolio(int seq){
		return portfolioMapper.selectPortfolio(seq);
	}
	
	@Override
	public int insertPortfolio(Portfolio portfolio){
		return portfolioMapper.insertPortfolio(portfolio);
	}
	
	@Override
	public int updatePortfolio(Portfolio portfolio){
		return portfolioMapper.updatePortfolio(portfolio);
	}
	@Override
	public int deletePortfolio(int seq){
		return portfolioMapper.deletePortfolio(seq);
	}

}
