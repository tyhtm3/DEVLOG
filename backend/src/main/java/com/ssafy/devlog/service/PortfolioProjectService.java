package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PortfolioProject;
import com.ssafy.devlog.dto.Project;

public interface PortfolioProjectService {
	
	public List<Project> selectAllPortfolioProject(int seq_post_portfolio);
	public int insertPortfolioProject(int seq_post_portfolio,List<Integer> seq_post_project);
	public int deletePortfolioProject(int seq_post_portfolio, int seq_post_project);
	public int deleteAllPortfolioProject(int seq_post_portfolio);
}
