package com.semi.project.login.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.semi.project.board.dto.AppendDTO;
import com.semi.project.board.entity.Append;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;
import com.semi.project.login.repository.MemberRepository;
import com.semi.project.mypage.repository.AppendRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final AppendRepository appendRepository;
	
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder
			,AppendRepository appendRepository) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.appendRepository = appendRepository;
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

	
	public String findIdByMemberIdAndMemberNameAndMemberEmail(String memberId, String memberName, String memberEmail) {
		
		Member member = memberRepository.findIdByMemberIdAndMemberNameAndMemberEmail(memberId, memberName, memberEmail);
		
		if (member == null) {
			
			return null;
		}
		return member.getMemberEmail();
	}

	/** 임시 비밀번호로 업데이트 **/
	public void changeTempPw(MemberDTO updatepassword) {
		
		Member savedMember = memberRepository.findByMemberId(updatepassword.getMemberId());
		savedMember.setMemberPwd(updatepassword.getMemberPwd());

	}
	
	/* 이미지를 저장하기 위한 메소드*/
	public void registThumbnail(AppendDTO memberProfile) {
		
		appendRepository.save(modelMapper.map(memberProfile, Append.class));
		log.info("werwer : {}", memberProfile.getMember());
		
	}
    
	/* 비밀번호 변경 */
	public String updatePassword(MemberDTO member) {
		
		Member savedMember = memberRepository.findByMemberId(member.getMemberId());
		savedMember.setMemberPwd(member.getMemberPwd());
		
		log.info("{member.getMemberPwd}",member.getMemberPwd());
		return member.getMemberPwd();
	}
	
	
}
