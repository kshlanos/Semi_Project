package com.semi.project.login.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;
import com.semi.project.login.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
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

	public String findIdByMemberNameAndMemberPhone(String memberName, String memberPhone) {
		
		Member member = memberRepository.findIdByMemberNameAndMemberPhone(memberName, memberPhone);
		
		 if (member == null) {
	            return null;
		 }
		 
		 
	       return member.getMemberId();
	    }
	
//    /** 임시 비밀번호로 업데이트 **/
//    public void updatePassword(String tmpPassword, String memberEmail) {
//
//        String encryptPassword = passwordEncoder.encode(tmpPassword);
//        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() ->
//                new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
//
//        member.updatePassword(encryptPassword);
//        log.info("임시 비밀번호 업데이트");
//    }

	
	public String findIdByMemberIdAndMemberNameAndMemberEmail(String memberId, String memberName, String memberEmail) {
		
		Member member = memberRepository.findIdByMemberIdAndMemberNameAndMemberEmail(memberId, memberName, memberEmail);
		
		if (member == null) {
			
			return null;
		}
		return member.getMemberEmail();
	}

	public void changeTempPw(String tempPw, String memberId) {
		
		Member savedMember = memberRepository.findByMemberPwdAndMemberId(tempPw,memberId);
		log.info(tempPw);
		
		
	}
    
	
	
}
