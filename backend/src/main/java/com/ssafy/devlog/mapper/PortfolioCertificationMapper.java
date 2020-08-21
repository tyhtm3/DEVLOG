package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.PortfolioCertification;

@Mapper
public interface PortfolioCertificationMapper {

	public List<PortfolioCertification> selectAllPortfolioCertification(int seq_post_portfolio);
	public int insertPortfolioCertification(@Param("seq_post_portfolio")int seq_post_portfolio,@Param("certification")List<Object> certification);
	public int deletePortfolioCertification(int seq_post_portfolio);
	
}
