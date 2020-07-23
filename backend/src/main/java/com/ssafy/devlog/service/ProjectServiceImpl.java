package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.dto.Project;
import com.ssafy.devlog.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<Project> selectAllProject(){
		return projectMapper.selectAllProject();
	}
	@Override
	public List<Project> selectAllProjectByNeighbor(int seq_user){
		return projectMapper.selectAllProjectByNeighbor(seq_user);
	}
	@Override
	public List<Project> selectAllProjectByTag(List<String> tag){
		return projectMapper.selectAllProjectByTag(tag);
	}
	
	@Override
	public List<Project> selectAllProjectByBlog(int seq_blog){
		return projectMapper.selectAllProjectByBlog(seq_blog);
	}
	
	@Override
	public List<Project> selectAllProjectByBlogByTag(int seq_blog, List<String> tag){
		return projectMapper.selectAllProjectByBlogByTag(seq_blog, tag);
	}
	
	@Override
	public Project selectProject(int seq){
		return projectMapper.selectProject(seq);
	}
	
	@Override
	public int insertProject(Project project){
		return projectMapper.insertProject(project);
	}
	
	@Override
	public int updateProject(Project project){
		return projectMapper.updateProject(project);
	}
	@Override
	public int deleteProject(int seq){
		return projectMapper.deleteProject(seq);
	}

}
