package com.semi.project.login.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;
import com.semi.project.login.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}

	public boolean selectMemberById(String memberId) {
		
		return memberRepository.findByMemberIdAndMemberStatus(memberId, "N").isPresent();
	}

	public void registMember(MemberDTO member) {
		
		memberRepository.save(modelMapper.map(member, Member.class));
	}

	public void modifyMember(MemberDTO updateMember) {
		
		Member savedMember = memberRepository.findByMemberNo(updateMember.getMemberNo());
		savedMember.setMemberNickname(updateMember.getMemberNickname());
		savedMember.setMemberAddress(updateMember.getMemberAddress());
		savedMember.setMemberEmail(updateMember.getMemberEmail());
	

	}
	
	public void removeMember(MemberDTO member) {
    	
    	Member savedMember = memberRepository.findByMemberNo(member.getMemberNo());
    	savedMember.setMemberStatus("Y");

    }
}
