package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Certification;
import com.ssafy.devlog.mapper.CertificationMapper;

@Service
public class CertificationServiceImpl implements CertificationService {

	@Autowired
	private CertificationMapper certificationMapper;

	@Override
	public List<Certification> selectAllCertification() {
		return certificationMapper.selectAllCertification();
	}

	@Override
	public int insertCertification(Certification certification) {
		return certificationMapper.insertCertification(certification);
	}

}
