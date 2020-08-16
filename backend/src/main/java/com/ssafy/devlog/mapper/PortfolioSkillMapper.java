package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PortfolioSkill;

@Mapper
public interface PortfolioSkillMapper {

	public List<PortfolioSkill> selectAllPortfolioSkill(int seq_post_portfolio);
	public int insertPortfolioSkill(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("skill")List<Object> skill);
	public int deletePortfolioSkill(int seq_post_portfolio);
	
}
