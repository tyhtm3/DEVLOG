package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PortfolioProject;
import com.ssafy.devlog.dto.Project;

@Mapper
public interface PortfolioProjectMapper {

	public List<Project> selectAllPortfolioProject(int seq_post_portfolio);
	public int insertPortfolioProject(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("seq_post_project")List<Integer> seq_post_project);
	public int deletePortfolioProject(@Param("seq_post_portfolio")int seq_post_portfolio, @Param("seq_post_project") int seq_post_project);
	public int deleteAllPortfolioProject(int seq_post_portfolio);
	
}
