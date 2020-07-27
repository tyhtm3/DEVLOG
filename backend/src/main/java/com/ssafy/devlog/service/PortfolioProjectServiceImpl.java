package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PortfolioProject;
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
	public int insertPortfolioProject(PortfolioProject portfolioProject) {
		return portfolioProjectMapper.insertPortfolioProject(portfolioProject);
	}

	@Override
	public int deletePortfolioProject(int seq) {
		return portfolioProjectMapper.deletePortfolioProject(seq);
	}

}
