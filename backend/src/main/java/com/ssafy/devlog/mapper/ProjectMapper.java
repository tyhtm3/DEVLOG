package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Project;

@Mapper
public interface ProjectMapper {
	
	public List<Project> selectAllProject(int seq_user);
	public List<Project> selectAllProjectByNeighbor(int seq_user);
	public List<Project> selectAllProjectByTag(@Param("seq_user") int seq_user, @Param("tag") List<String> tag);
	
	public List<Project> selectAllProjectByBlog(@Param("seq_user") int seq_user, @Param("seq_blog") int seq_blog);
	public List<Project> selectAllProjectByBlogByTag(@Param("seq_user") int seq_user, @Param("seq_blog") int seq_blog, @Param("tag") List<String> tag);
	
	public Project selectProject(int seq);
	public int insertPost(Project project);
	public int insertPostProject(Project project);
	public int updatePost (Project project);
	public int updatePostProject(Project project);
	public int deleteProject(int seq);
	

}
