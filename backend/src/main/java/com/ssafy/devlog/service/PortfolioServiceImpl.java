package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.dto.Portfolio;
import com.ssafy.devlog.dto.Portfolio;
import com.ssafy.devlog.mapper.PortfolioMapper;

@Service
public class PortfolioServiceImpl implements PortfolioService{

	@Autowired
	private PortfolioMapper portfolioMapper;
	
		
	@Override
	public List<Portfolio> selectPortfolioByBlog(int seq_user,int seq_blog,int offset,int limit){
		return portfolioMapper.selectPortfolioByBlog(seq_user, seq_blog, offset, limit);
	}

	
	
	@Override
	public Portfolio selectPortfolio(int seq){
		return portfolioMapper.selectPortfolio(seq);
	}
	
	@Override
	public Portfolio selectPortfolioByRep(int seq_blog) {
		return portfolioMapper.selectPortfolioByRep(seq_blog);
	}
	
	@Override
	public int insertPost(Portfolio portfolio){
		return portfolioMapper.insertPost(portfolio);
	}
	
	@Override
	public int insertPostPortfolio(Portfolio portfolio){
		return portfolioMapper.insertPostPortfolio(portfolio);
	}
	
	@Override
	public int updatePost(Portfolio portfolio){
		return portfolioMapper.updatePost(portfolio);
	}
	
	@Override
	public int updatePostPortfolio(Portfolio portfolio) {
		return portfolioMapper.updatePostPortfolio(portfolio);
	}
	
	@Override
	public int updatePortfolioGeneral(int seq_blog) {
		return portfolioMapper.updatePortfolioGeneral(seq_blog);
	}
	
	@Override
	public int updatePortfolioRepresentation(int seq) {
		return portfolioMapper.updatePortfolioRepresentation(seq);
	}
	@Override
	public int deletePortfolio(int seq){
		return portfolioMapper.deletePortfolio(seq);
	}

}
