package com.semi.project.mypage.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semi.project.mypage.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {


	Page<Inquiry> findByInquiryNo(int textBoardType, String activeStatus, Pageable pageable);
	
	/* 문의내역 조회 쿼리문. */
	@Query("SELECT i FROM Inquiry i WHERE i.inquiryDelete = 'N' and i.member.memberNo = :memberNo")
	Page<Inquiry> findInquiryList(@Param("memberNo") Long memberNo, Pageable pageable);
	
	/* 문의 상세페이지 조회 쿼리문 */
	@Query("SELECT i FROM Inquiry i WHERE i.inquiryDelete= 'N' and (i.inquiryNo = :inquiryNo or i.inquiryRefNo = :inquiryNo) order by i.inquiryRefNo nulls first")
	List<Inquiry> findInquiry(@Param("inquiryNo") Long inquiryNo);
	
	
	
	
	
}
