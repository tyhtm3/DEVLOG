package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Project;

@Mapper
public interface ProjectMapper {
	
	public List<Project> selectProjectByFeed(@Param("seq_user") int seq_user,@Param("disclosure")int disclosure, @Param("tag") List<String> tag);
	public List<Project> selectProjectByBlog(@Param("seq_user") int seq_user,@Param("seq_blog")int seq_blog, @Param("offset") int offset, @Param("limit") int limit, @Param("tag") List<String> tag);
	public int selectProjectCntByBlog(@Param("seq_user") int seq_user,@Param("seq_blog")int seq_blog);
	
	public Project selectProject(int seq);
	public List<Project> selectDraftProject(int seq_blog);
	public int insertPost(Project project);
	public int insertPostProject(Project project);
	public int updatePost (Project project);
	public int updatePostProject(Project project);
	public int deleteProject(int seq);
	

}
