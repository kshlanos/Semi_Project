package com.semi.project.admin.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.entity.Inquiry;

public interface QnaRepository extends JpaRepository<Inquiry, Long> {
	

	Page<Inquiry> findByInquiryRefNoAndInquiryDelete(Long inquiryRefNo, String inquiryDelete, Pageable pageable);

	Inquiry findByInquiryNo(Long inquiryNo);
	


}
