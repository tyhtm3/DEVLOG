package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<Project> selectAllProject(int seq_user){
		return projectMapper.selectAllProject(seq_user);
	}
	@Override
	public List<Project> selectAllProjectByNeighbor(int seq_user){
		return projectMapper.selectAllProjectByNeighbor(seq_user);
	}
	@Override
	public List<Project> selectAllProjectByTag(int seq_user, List<String> tag){
		return projectMapper.selectAllProjectByTag(seq_user, tag);
	}
	
	@Override
	public List<Project> selectAllProjectByBlog(int seq_user, int seq_blog){
		return projectMapper.selectAllProjectByBlog(seq_user, seq_blog);
	}
	
	@Override
	public List<Project> selectAllProjectByBlogByTag(int seq_user, int seq_blog, List<String> tag){
		return projectMapper.selectAllProjectByBlogByTag(seq_user, seq_blog, tag);
	}
	
	@Override
	public Project selectProject(int seq){
		return projectMapper.selectProject(seq);
	}
	
	@Override
	public int insertPost(Project project){
		return projectMapper.insertPost(project);
	}
	
	@Override
	public int insertPostProject(Project project){
		return projectMapper.insertPostProject(project);
	}
	
	@Override
	public int updatePost(Project project){
		return projectMapper.updatePost(project);
	}
	
	@Override
	public int updatePostProject(Project project) {
		return projectMapper.updatePostProject(project);
	}
	
	@Override
	public int deleteProject(int seq){
		return projectMapper.deleteProject(seq);
	}

}
