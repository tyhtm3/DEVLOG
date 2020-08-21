package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Socialicon;
import com.ssafy.devlog.dto.UserStack;
import com.ssafy.devlog.mapper.SocialiconMapper;
import com.ssafy.devlog.mapper.UserStackMapper;

@Service
public class SocialiconServiceImpl implements SocialiconService {

	@Autowired
	private SocialiconMapper socialiconMapper;

	@Override
	public List<Socialicon> selectAllSocialicon(){
		return socialiconMapper.selectAllSocialicon();
	};	

}
