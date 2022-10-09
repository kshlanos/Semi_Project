package com.semi.project.login.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data  
public class MemberDTO implements UserDetails{
 
    private Long memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberGender;
    private String memberNickname;
    private Date memberBirth;
    private String memberAddress;
    private String memberEmail;
    private String memberRating;
    private String memberStatus;
    private Date memberDeletedate; 
    private String memberRole;
    
  
    @Override
    public String toString() {
        return "MemberDto{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberGender='" + memberGender + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", memberBirth='" + memberBirth + '\'' +
                ", memberAddress=" + memberAddress +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberRating='" + memberRating + '\'' +
                ", memberStatus='" + memberStatus + '\'' +
                ", memberDeletedate='" + memberDeletedate + '\'' +
                ", memberRole='" + memberRole + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(memberRole));
        return roles;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠기지 않음
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료되지 않음
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true; // 활성화
    }
    
}
