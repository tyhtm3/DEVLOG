package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.ProjectRole;

@Mapper
public interface ProjectRoleMapper {

	public List<ProjectRole> selectAllProjectRole(int seq_post_project);
	public int insertProjectRole(@Param("seq_post_project")int seq_post_project, @Param("role")List<String> role);
	public int deleteProjectRole(int seq_post_project);
	
}
