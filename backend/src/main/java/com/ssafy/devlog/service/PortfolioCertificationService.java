package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.PortfolioCertification;


public interface PortfolioCertificationService {
	
	public List<PortfolioCertification> selectAllPortfolioCertification(int seq_post_portfolio);
	public int insertPortfolioCertification(int seq_post_portfolio,List<Object> certification);
	public int deletePortfolioCertification(int seq_post_portfolio);
}
