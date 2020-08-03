package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.ProjectStack;

@Mapper
public interface ProjectStackMapper {

	public List<ProjectStack> selectAllProjectStack(int seq_post_project);
	public List<ProjectStack> selectProjectImageStack(int seq_post_project);
	public List<ProjectStack> selectProjectTextStack(int seq_post_project);
	public ProjectStack selectProjectStackByProjectAndStack(ProjectStack projectStack);
	public int insertProjectStack(ProjectStack projectStack);
	public int updateProjectStackImg(ProjectStack projectStack);
	public int deleteProjectStack(int seq);
	
}
