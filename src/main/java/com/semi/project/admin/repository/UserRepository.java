package com.semi.project.admin.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.login.entity.Member;

public interface UserRepository extends JpaRepository<Member, Long>{

	Page<Member> findByMemberStatus(String memberStatus, Pageable pageable);

	
	Member findByMemberNo(Long memberNo);

//	Page<Member> findByAll(Pageable pageable);

	

	

}
