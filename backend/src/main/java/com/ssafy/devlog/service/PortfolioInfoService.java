package com.ssafy.devlog.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PortfolioEducation;
import com.ssafy.devlog.dto.PortfolioExperience;
import com.ssafy.devlog.dto.PortfolioInfo;
import com.ssafy.devlog.dto.PortfolioSocial;

public interface PortfolioInfoService {
	
	//introduction, address, objective
	public PortfolioInfo selectAllPortfolioInfo(int seq_post_portfolio);
	public int insertPortfolioInfo(PortfolioInfo portfolioInfo);
	public int deletePortfolioInfo(int seq_post_portfolio);
	
	//Experience
	public List<PortfolioExperience> selectAllPortfolioExperience(int seq_post_portfolio);
	public int insertPortfolioExperience(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("experience")List<Object> experience);
	public int deletePortfolioExperience(int seq_post_portfolio);
	
	//Education
	public List<PortfolioEducation> selectAllPortfolioEducation(int seq_post_portfolio);
	public int insertPortfolioEducation(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("education")List<Object> education);
	public int deletePortfolioEducation(int seq_post_portfolio);
	
	//Social
	public List<PortfolioSocial> selectAllPortfolioSocial(int seq_post_portfolio);
	public int insertPortfolioSocial(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("social")List<Object> social);
	public int deletePortfolioSocial(int seq_post_social);

}
