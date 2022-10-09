package com.semi.project.mypage.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.entity.Inquiry;
import com.semi.project.mypage.repository.InquiryRepository;

@Service

public class InquiryService {

	public final InquiryRepository inquiryRepository;
	public final ModelMapper modelMapper;
	
	public InquiryService(InquiryRepository inquiryRepository, ModelMapper modelMapper ) {
		this.inquiryRepository = inquiryRepository;
		this.modelMapper = modelMapper;
	}
	
	public void inquiryBoard(InquiryDTO inquiry) {
		
		inquiryRepository.save(modelMapper.map(inquiry, Inquiry.class));
		
	}

}
