package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.ProjectStack;
import com.ssafy.devlog.mapper.ProjectStackMapper;

@Service
public class ProjectStackServiceImpl implements ProjectStackService {
	
	@Autowired
	private ProjectStackMapper projectStackMapper;
	

	@Override
	public List<ProjectStack> selectAllProjectStack(int seq_post_project){
		return projectStackMapper.selectAllProjectStack(seq_post_project);
	}
	
	@Override
	public int insertProjectStack(ProjectStack projectStack) {
		return projectStackMapper.insertProjectStack(projectStack);
	}

	@Override
	public int deleteProjectStack(int seq) {
		return projectStackMapper.deleteProjectStack(seq);
	}

}
