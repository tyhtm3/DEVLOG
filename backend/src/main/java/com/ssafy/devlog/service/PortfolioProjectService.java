package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PortfolioProject;
import com.ssafy.devlog.dto.Project;

public interface PortfolioProjectService {
	
	public List<Project> selectAllPortfolioProject(int seq_post_portfolio);
	public int insertPortfolioProject(PortfolioProject portfolioProject);
	public int deletePortfolioProject(int seq_post_portfolio, int seq_post_project);
}
