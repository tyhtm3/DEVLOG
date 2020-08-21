package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PortfolioLanguage;
import com.ssafy.devlog.mapper.PortfolioLanguageMapper;

@Service
public class PortfolioLanguageServiceImpl implements PortfolioLanguageService {
	
	@Autowired
	private PortfolioLanguageMapper portfolioLanguageMapper;
	

	@Override
	public List<PortfolioLanguage> selectAllPortfolioLanguage(int seq_post_portfolio){
		return portfolioLanguageMapper.selectAllPortfolioLanguage(seq_post_portfolio);
	}
	
	@Override
	public int insertPortfolioLanguage(int seq_post_portfolio,List<Object> language) {
		return portfolioLanguageMapper.insertPortfolioLanguage(seq_post_portfolio, language);
	}

	@Override
	public int deletePortfolioLanguage(int seq_post_portfolio) {
		return portfolioLanguageMapper.deletePortfolioLanguage(seq_post_portfolio);
	}

}
