package com.semi.project.login.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.semi.project.login.entity.Member;

import lombok.Getter;


@Getter
public class CustomUser extends User {

	private Long memberNo;					// 회원번호
	
	public CustomUser(Member member, Collection<? extends GrantedAuthority> authorities) {
		super(member.getMemberId(), member.getMemberPwd(), authorities);
		setDetails(member);
	
	}

	private void setDetails(Member member) {
		this.memberNo=member.getMemberNo();
		
	}

}
