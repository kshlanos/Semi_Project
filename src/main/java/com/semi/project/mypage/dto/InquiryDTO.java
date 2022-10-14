package com.semi.project.mypage.dto;

import java.util.Date;

import com.semi.project.login.dto.MemberDTO;

import lombok.Data;

@Data
public class InquiryDTO {
	
	private Long inquiryNo;
	private String inquiryTitle;
	private String inquiryContent;
	private Date inquiryRegDate;
	private String inquiryDelete;
	private Date inquiryModDate;
	private Long inquiryRefNo;
	private MemberDTO member;
	private String inquiryStatus;
	private String inquiryStatusContent;
	
	
	
	
}
