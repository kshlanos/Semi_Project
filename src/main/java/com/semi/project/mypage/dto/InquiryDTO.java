package com.semi.project.mypage.dto;

import java.util.Date;

public class InquiryDTO {
	
	private Long inquiryNo;
	private String inquiryTitle;
	private String inquiryContent;
	private String inquiryDelete;
	private Date inquiryModDate;
	private Long inquiryRefNo;
	private Long memberNo;
	
	InquiryDTO () {}

	public InquiryDTO(Long inquiryNo, String inquiryTitle, String inquiryContent, String inquiryDelete,
			Date inquiryModDate, Long inquiryRefNo, Long memberNo) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquiryTitle = inquiryTitle;
		this.inquiryContent = inquiryContent;
		this.inquiryDelete = inquiryDelete;
		this.inquiryModDate = inquiryModDate;
		this.inquiryRefNo = inquiryRefNo;
		this.memberNo = memberNo;
	}

	public Long getInquiryNo() {
		return inquiryNo;
	}

	public void setInquiryNo(Long inquiryNo) {
		this.inquiryNo = inquiryNo;
	}

	public String getInquiryTitle() {
		return inquiryTitle;
	}

	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}

	public String getInquiryContent() {
		return inquiryContent;
	}

	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}

	public String getInquiryDelete() {
		return inquiryDelete;
	}

	public void setInquiryDelete(String inquiryDelete) {
		this.inquiryDelete = inquiryDelete;
	}

	public Date getInquiryModDate() {
		return inquiryModDate;
	}

	public void setInquiryModDate(Date inquiryModDate) {
		this.inquiryModDate = inquiryModDate;
	}

	public Long getInquiryRefNo() {
		return inquiryRefNo;
	}

	public void setInquiryRefNo(Long inquiryRefNo) {
		this.inquiryRefNo = inquiryRefNo;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "InquiryDTO [inquiryNo=" + inquiryNo + ", inquiryTitle=" + inquiryTitle + ", inquiryContent="
				+ inquiryContent + ", inquiryDelete=" + inquiryDelete + ", inquiryModDate=" + inquiryModDate
				+ ", inquiryRefNo=" + inquiryRefNo + ", memberNo=" + memberNo + "]";
	}
	
	
}
