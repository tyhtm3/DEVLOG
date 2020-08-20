package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.devlog.dto.Portfolio;

@Mapper
public interface PortfolioMapper {
	
	public List<Portfolio> selectPortfolioByBlog(@Param("seq_user") int seq_user, @Param("seq_blog") int seq_blog,@Param("offset") int offset, @Param("limit") int limit);

	public Portfolio selectPortfolio(int seq);
	public Portfolio selectPortfolioByRep(int seq_blog);
	public int insertPost(Portfolio portfolio);
	public int insertPostPortfolio(Portfolio portfolio);
	public int updatePost (Portfolio portfolio);
	public int updatePostPortfolio(Portfolio portfolio);
	public int updatePortfolioGeneral(int seq_blog);
	public int updatePortfolioRepresentation(int seq);
	public int deletePortfolio(int seq);
	
}
