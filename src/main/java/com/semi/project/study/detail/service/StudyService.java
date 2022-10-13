package com.semi.project.study.detail.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.project.study.detail.dto.StudyDTO;
import com.semi.project.study.detail.entity.Study;
import com.semi.project.study.detail.repository.StudyRepository;

@Service
@Transactional
public class StudyService {
	
	private final StudyRepository studyRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public StudyService(StudyRepository studyRepository, ModelMapper modelMapper) {
		this.studyRepository = studyRepository;
		this.modelMapper = modelMapper;
		
	}
	
	public List<StudyDTO> selectAllStudy(Long memberNo) {

		List<Study> studyList = studyRepository.findByMemberNo(memberNo);
		
		return studyList.stream().map(study -> modelMapper.map(study, StudyDTO.class)).collect(Collectors.toList());
	}

}
