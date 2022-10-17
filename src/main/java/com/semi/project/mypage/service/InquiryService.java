package com.semi.project.mypage.service;



import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.semi.project.login.dto.MemberDTO;
import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.entity.Inquiry;
import com.semi.project.mypage.repository.InquiryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InquiryService {

	public final InquiryRepository inquiryRepository;
	public final ModelMapper modelMapper;
	
	
	public static final int TEXT_PAGE_SIZE = 5;
	public static final String SORT_BY = "inquiryNo";
	
	
	
	public InquiryService(InquiryRepository inquiryRepository, ModelMapper modelMapper ) {
		this.inquiryRepository = inquiryRepository;
		this.modelMapper = modelMapper;
	}
	
	public void inquiryBoard(InquiryDTO inquiry) {
		
		inquiryRepository.save(modelMapper.map(inquiry, Inquiry.class));
		
	}
	
	/* 문의내역 페이징 처리 및 조회 서비스*/
	public Page<InquiryDTO> selectInquiryList(int page, MemberDTO member) {
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Inquiry> inquiryList = inquiryRepository.findInquiryList(member.getMemberNo(), pageable);
		
		return inquiryList.map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class));
	}
	
	/* 문의글 수정 시 이전에 작성된 문의글 내용 조회 서비스 */
	public List<InquiryDTO> inquiryconfirm(Long inquiryNo) {
		
		List<Inquiry> inquiryList = inquiryRepository.findInquiry(inquiryNo);
		
		log.info("inquiryList : {}", inquiryList);
		
		return inquiryList.stream().map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class)).collect(Collectors.toList());
	}
	
	/* 문의글 수정 서비스*/
	public void inquiryChange(InquiryDTO inquiry) {
		
		Inquiry changeinquiry = inquiryRepository.findById(inquiry.getInquiryNo()).get();
		changeinquiry.setInquiryTitle(inquiry.getInquiryTitle());
		changeinquiry.setInquiryContent(inquiry.getInquiryContent());
		changeinquiry.setInquiryModDate(new Date(System.currentTimeMillis()));
		
	}
	
	/* 문의글 삭제 서비스*/
	public void inquirydelete(Long inquiryNo) {
		
		Inquiry deleteInquiry = inquiryRepository.findById(inquiryNo).get();
		deleteInquiry.setInquiryDelete("Y");
		
	}
}

//	public InquiryDTO inquirymodify(Long inquiryNo) {
//	
//List<Inquiry> inquiryList = inquiryRepository.findInquiry(inquiryNo);
//		
//		log.info("inquiryList : {}", inquiryList);
//		
//		//return inquiryList.stream().map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class)).collect(Collectors.toList());
//	}


