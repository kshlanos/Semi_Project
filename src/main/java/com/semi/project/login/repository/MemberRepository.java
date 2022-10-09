package com.semi.project.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.login.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	Optional<Member> findByMemberIdAndMemberStatus(String memberId, String memberStatus);

	Member findByMemberNo(Long memberNo);
	
}
