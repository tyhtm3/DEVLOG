package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PostTag;
import com.ssafy.devlog.dto.ProjectStack;

public interface ProjectStackService {
	
	public List<ProjectStack> selectAllProjectStack(int seq_post_project);
	public ProjectStack selectProjectStackByProjectAndStack(ProjectStack projectStack);
	public int insertProjectStack(ProjectStack projectStack);
	public int deleteProjectStack(int seq);
}
