package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.Project;

public interface ProjectService {

	public List<Project> selectProjectByFeed(int seq_user,int disclosure,List<String> tag);
	public List<Project> selectProjectByBlog(int seq_user,int seq_blog,int offset,int limit,List<String> tag);
	public int selectProjectCntByBlog(int seq_user,int seq_blog);
	
	public Project selectProject(int seq);
	public List<Project> selectDraftProject(int seq_blog);
	public int insertPost(Project project);
	public int insertPostProject(Project project);
	public int updatePost (Project project);
	public int updatePostProject(Project project);
	public int deleteProject(int seq);
	
}
