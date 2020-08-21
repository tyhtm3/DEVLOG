package com.ssafy.devlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.devlog.dto.Certification;

@Mapper
public interface CertificationMapper {

	public List<Certification> selectAllCertification();
	public int insertCertification(Certification certification);
	
}
