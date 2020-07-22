package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Project;

@Mapper
public interface ProjectMapper {
	
	public List<Project> selectAllProject();
	public List<Project> selectAllProjectByNeighbor(int seq_user);
	public List<Project> selectAllProjectByTag(List<String> tag);
	
	public List<Project> selectAllProjectByBlog(int seq_blog);
	public List<Project> selectAllProjectByBlogByTag(@Param("seq_blog") int seq_blog, @Param("tag") List<String> tag);
	
	public Project selectProject(int seq);
	public int insertProject(@Param("seq_blog") int seq_blog, @Param("project") Project project);
	public int updateProject(Project project);
	public int deleteProject(int seq);
	

}
