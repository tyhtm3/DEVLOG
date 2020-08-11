package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.ProjectRole;
import com.ssafy.devlog.mapper.ProjectRoleMapper;

@Service
public class ProjectRoleServiceImpl implements ProjectRoleService {
	
	@Autowired
	private ProjectRoleMapper projectRoleMapper;

	@Override
	public List<ProjectRole> selectAllProjectRole(int seq_post_project){
		return projectRoleMapper.selectAllProjectRole(seq_post_project);
	}
	
	@Override
	public int insertProjectRole(int seq_post_project,List<String> role) {
		return projectRoleMapper.insertProjectRole(seq_post_project, role);
	}
	
	@Override
	public int deleteProjectRole(int seq_post_project) {
		return projectRoleMapper.deleteProjectRole(seq_post_project);
	}
}
