package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PortfolioCertification;
import com.ssafy.devlog.dto.PortfolioLanguage;

@Mapper
public interface PortfolioLanguageMapper {

	public List<PortfolioLanguage> selectAllPortfolioLanguage(int seq_post_portfolio);
	public int insertPortfolioLanguage(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("language")List<Object> language);
	public int deletePortfolioLanguage(int seq_post_portfolio);
	
}
