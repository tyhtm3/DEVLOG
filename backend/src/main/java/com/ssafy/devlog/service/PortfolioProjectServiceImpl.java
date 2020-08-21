package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.mapper.PortfolioProjectMapper;

@Service
public class PortfolioProjectServiceImpl implements PortfolioProjectService {
	
	@Autowired
	private PortfolioProjectMapper portfolioProjectMapper;
	

	@Override
	public List<Project> selectAllPortfolioProject(int seq_post_portfolio){
		return portfolioProjectMapper.selectAllPortfolioProject(seq_post_portfolio);
	}
	
	@Override
	public int insertPortfolioProject(int seq_post_portfolio,List<Integer> seq_post_project) {
		return portfolioProjectMapper.insertPortfolioProject(seq_post_portfolio,seq_post_project);
	}

	@Override
	public int deletePortfolioProject(int seq_post_portfolio, int seq_post_project) {
		return portfolioProjectMapper.deletePortfolioProject(seq_post_portfolio , seq_post_project);
	}

	@Override
	public int deleteAllPortfolioProject(int seq_post_portfolio) {
		return portfolioProjectMapper.deleteAllPortfolioProject(seq_post_portfolio);
	}

}
