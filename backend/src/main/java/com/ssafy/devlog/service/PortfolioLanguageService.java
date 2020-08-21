package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PortfolioLanguage;


public interface PortfolioLanguageService {
	
	public List<PortfolioLanguage> selectAllPortfolioLanguage(int seq_post_portfolio);
	public int insertPortfolioLanguage(int seq_post_portfolio,List<Object> language);
	public int deletePortfolioLanguage(int seq_post_portfolio);
}
