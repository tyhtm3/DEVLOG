package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PortfolioSkill;


public interface PortfolioSkillService {
	
	public List<PortfolioSkill> selectAllPortfolioSkill(int seq_post_portfolio);
	public int insertPortfolioSkill(int seq_post_portfolio,List<Object> skill);
	public int deletePortfolioSkill(int seq_post_portfolio);
}
