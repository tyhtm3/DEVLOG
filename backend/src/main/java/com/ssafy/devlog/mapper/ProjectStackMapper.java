package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.ProjectStack;

@Mapper
public interface ProjectStackMapper {

	public List<ProjectStack> selectAllProjectStack(int seq_post_project);
	public int insertProjectStack(ProjectStack projectStack);
	public int deleteProjectStack(int seq);
	
}
