package com.semi.project.study.detail.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.project.study.detail.dto.StudyMemberDTO;
import com.semi.project.study.detail.entity.StudyMember;
import com.semi.project.study.detail.repository.StudyMemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StudyMemberService {

	private final StudyMemberRepository studyMemberRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public StudyMemberService (StudyMemberRepository studyMemberRepository, ModelMapper modelMapper) {
		this.studyMemberRepository = studyMemberRepository;
		this.modelMapper = modelMapper;
	}
	
	public String selectStudyId(int studyNo, Long memberNo) {
		
		List<StudyMember> studyMember = studyMemberRepository.findByMemberNo(memberNo);
		
		
		List<StudyMemberDTO> studyList = studyMember.stream().map(study -> modelMapper.map(study, StudyMemberDTO.class)).collect(Collectors.toList());
		
		
		String studyId = studyList.get(0).getStudyId();
		
		
		return studyId;
	}

	public List<StudyMemberDTO> selectAllStudy(Long memberNo) {

		List<StudyMember> studyList = studyMemberRepository.findByMemberNo(memberNo);
		
    	log.info("[MemberService] studyList : {}", studyList);
		
		return studyList.stream().map(study -> modelMapper.map(study, StudyMemberDTO.class)).collect(Collectors.toList());
	}

	public StudyMemberDTO selectRole(Long memberNo, String studyId) {

		StudyMember studyRole = studyMemberRepository.findByStudyIdAndMemberNo(studyId, memberNo);
		
		return modelMapper.map(studyRole, StudyMemberDTO.class);
	}

}
