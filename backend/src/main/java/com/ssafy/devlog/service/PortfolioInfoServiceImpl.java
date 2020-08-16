package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PortfolioEducation;
import com.ssafy.devlog.dto.PortfolioExperience;
import com.ssafy.devlog.dto.PortfolioInfo;
import com.ssafy.devlog.dto.PortfolioSocial;
import com.ssafy.devlog.mapper.PortfolioInfoMapper;

@Service
public class PortfolioInfoServiceImpl implements PortfolioInfoService {
	
	@Autowired
	private PortfolioInfoMapper portfolioInfoMapper;

	@Override
	public PortfolioInfo selectAllPortfolioInfo(int seq_post_portfolio) {
		return portfolioInfoMapper.selectAllPortfolioInfo(seq_post_portfolio);
	}

	@Override
	public int insertPortfolioInfo(PortfolioInfo portfolioInfo) {
		return portfolioInfoMapper.insertPortfolioInfo(portfolioInfo);
	}

	@Override
	public int deletePortfolioInfo(int seq_post_portfolio) {
		return portfolioInfoMapper.deletePortfolioInfo(seq_post_portfolio);
	}

	@Override
	public List<PortfolioExperience> selectAllPortfolioExperience(int seq_post_portfolio) {
		return portfolioInfoMapper.selectAllPortfolioExperience(seq_post_portfolio);
	}

	@Override
	public int insertPortfolioExperience(int seq_post_portfolio, List<Object> experience) {
		return portfolioInfoMapper.insertPortfolioExperience(seq_post_portfolio, experience);
	}

	@Override
	public int deletePortfolioExperience(int seq_post_portfolio) {
		return portfolioInfoMapper.deletePortfolioExperience(seq_post_portfolio);
	}

	@Override
	public List<PortfolioEducation> selectAllPortfolioEducation(int seq_post_portfolio) {
		return portfolioInfoMapper.selectAllPortfolioEducation(seq_post_portfolio);
	}

	@Override
	public int insertPortfolioEducation(int seq_post_portfolio, List<Object> education) {
		return portfolioInfoMapper.insertPortfolioEducation(seq_post_portfolio, education);
	}

	@Override
	public int deletePortfolioEducation(int seq_post_portfolio) {
		return portfolioInfoMapper.deletePortfolioEducation(seq_post_portfolio);
	}

	@Override
	public List<PortfolioSocial> selectAllPortfolioSocial(int seq_post_portfolio) {
		return portfolioInfoMapper.selectAllPortfolioSocial(seq_post_portfolio);
	}

	@Override
	public int insertPortfolioSocial(int seq_post_portfolio, List<Object> social) {
		return portfolioInfoMapper.insertPortfolioSocial(seq_post_portfolio, social);
	}

	@Override
	public int deletePortfolioSocial(int seq_post_social) {
		return portfolioInfoMapper.deletePortfolioSocial(seq_post_social);
	}

}
