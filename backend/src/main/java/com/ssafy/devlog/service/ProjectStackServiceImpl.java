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
	public List<ProjectStack> selectAllProjectStack(int seq_post_project) {
		return projectStackMapper.selectAllProjectStack(seq_post_project);
	}

	@Override
	public List<ProjectStack> selectProjectImageStack(int seq_post_project) {
		return projectStackMapper.selectProjectImageStack(seq_post_project);
	}

	@Override
	public List<ProjectStack> selectProjectTextStack(int seq_post_project) {
		return projectStackMapper.selectProjectTextStack(seq_post_project);
	}

	@Override
	public int insertProjectStack(ProjectStack projectStack) {
		return projectStackMapper.insertProjectStack(projectStack);
	}

	@Override
	public int updateProjectStackImg(ProjectStack projectStack) {
		return projectStackMapper.updateProjectStackImg(projectStack);
	}

	@Override
	public int deleteProjectStack(int seq) {
		return projectStackMapper.deleteProjectStack(seq);
	}


	@Override
	public ProjectStack selectProjectStackByProjectAndStack(ProjectStack projectStack) {
		return projectStackMapper.selectProjectStackByProjectAndStack(projectStack);
	}

}
