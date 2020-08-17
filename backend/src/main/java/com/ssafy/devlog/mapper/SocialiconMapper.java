package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.Socialicon;

@Mapper
public interface SocialiconMapper {

	public List<Socialicon> selectAllSocialicon();
	
}
