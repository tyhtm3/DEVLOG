package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PortfolioSkill;
import com.ssafy.devlog.mapper.PortfolioSkillMapper;

@Service
public class PortfolioSkillServiceImpl implements PortfolioSkillService {
	
	@Autowired
	private PortfolioSkillMapper portfolioSkillMapper;
	

	@Override
	public List<PortfolioSkill> selectAllPortfolioSkill(int seq_post_portfolio){
		return portfolioSkillMapper.selectAllPortfolioSkill(seq_post_portfolio);
	}
	
	@Override
	public int insertPortfolioSkill(int seq_post_portfolio,List<Object> skill) {
		return portfolioSkillMapper.insertPortfolioSkill(seq_post_portfolio, skill);
	}

	@Override
	public int deletePortfolioSkill(int seq_post_portfolio) {
		return portfolioSkillMapper.deletePortfolioSkill(seq_post_portfolio);
	}

}
