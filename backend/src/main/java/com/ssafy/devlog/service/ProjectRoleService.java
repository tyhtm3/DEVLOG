package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.ProjectRole;

public interface ProjectRoleService {
	
	public List<ProjectRole> selectAllProjectRole(int seq_post_project);
	public int insertProjectRole(int seq_post_project,List<String> role);
	public int deleteProjectRole(int seq_post_project);
}
