package com.semi.project.study.todo.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.semi.project.study.todo.dto.CertifiedDTO;
import com.semi.project.study.todo.entity.Certified;
import com.semi.project.study.todo.repository.CertifiedRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CertifiedService {

	private final ModelMapper modelMapper;
	private final CertifiedRepository certifiedRepository;
	
	public CertifiedService(ModelMapper modelMapper, CertifiedRepository certifiedRepository) {
		this.modelMapper = modelMapper;
		this.certifiedRepository = certifiedRepository;
	}
	
	public void registCertified(CertifiedDTO certified) {

		log.info("[CertifiedService] : {}", certified);
		
		certifiedRepository.save(modelMapper.map(certified, Certified.class));
		
	}

}
