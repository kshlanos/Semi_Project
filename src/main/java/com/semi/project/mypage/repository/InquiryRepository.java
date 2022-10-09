package com.semi.project.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.mypage.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
	
	
	
}
