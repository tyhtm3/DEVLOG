package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.Project;

public interface ProjectService {

	public List<Project> selectAllProject(int seq_user);
	public List<Project> selectAllProjectByNeighbor(int seq_user);
	public List<Project> selectAllProjectByTag(int seq_user, List<String> tag);

	public List<Project> selectAllProjectByBlog(int seq_user, int seq_blog);
	public List<Project> selectAllProjectByBlogByTag(int seq_user, int seq_blog, List<String> tag);
	
	public Project selectProject(int seq);
	public int insertPost(Project project);
	public int insertPostProject(Project project);
	public int updatePost (Project project);
	public int updatePostProject(Project project);
	public int deleteProject(int seq);
	
}
