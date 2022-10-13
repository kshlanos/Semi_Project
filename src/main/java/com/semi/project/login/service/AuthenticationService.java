package com.semi.project.login.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;
import com.semi.project.login.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
@Transactional
public class AuthenticationService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public AuthenticationService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        log.info("[AuthenticationService] =====================================================");
        log.info("[AuthenticationService] memberId : " + memberId);
       

        Member selectedMember = memberRepository.findByMemberIdAndMemberStatus(memberId, "N").orElseThrow(() -> new UsernameNotFoundException("회원 정보가 존재하지 않습니다."));

        MemberDTO member = modelMapper.map(selectedMember, MemberDTO.class);
        
        log.info("[AuthenticationService] member : " + member); 
        
        return member;
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
