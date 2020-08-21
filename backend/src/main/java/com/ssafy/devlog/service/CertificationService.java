package com.ssafy.devlog.service;

import java.util.List;

import com.ssafy.devlog.dto.Certification;

public interface CertificationService {

	public List<Certification> selectAllCertification();
	public int insertCertification(Certification certification);
}
