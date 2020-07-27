package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.PortfolioProject;
import com.ssafy.devlog.dto.Project;

@Mapper
public interface PortfolioProjectMapper {

	public List<Project> selectAllPortfolioProject(int seq_post_portfolio);
	public int insertPortfolioProject(PortfolioProject portfolioProject);
	public int deletePortfolioProject(int seq);
	
}
