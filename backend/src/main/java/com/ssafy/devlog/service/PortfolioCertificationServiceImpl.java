package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.PortfolioCertification;
import com.ssafy.devlog.mapper.PortfolioCertificationMapper;

@Service
public class PortfolioCertificationServiceImpl implements PortfolioCertificationService {
	
	@Autowired
	private PortfolioCertificationMapper portfolioCertificationMapper;
	

	@Override
	public List<PortfolioCertification> selectAllPortfolioCertification(int seq_post_portfolio){
		return portfolioCertificationMapper.selectAllPortfolioCertification(seq_post_portfolio);
	}
	
	@Override
	public int insertPortfolioCertification(int seq_post_portfolio,List<Object> certification) {
		return portfolioCertificationMapper.insertPortfolioCertification(seq_post_portfolio, certification);
	}

	@Override
	public int deletePortfolioCertification(int seq_post_portfolio) {
		return portfolioCertificationMapper.deletePortfolioCertification(seq_post_portfolio);
	}

}
