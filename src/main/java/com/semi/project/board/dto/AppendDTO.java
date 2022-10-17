package com.semi.project.board.dto;

import javax.persistence.Column;

import com.semi.project.admin.dto.NoticeDTO;
import com.semi.project.login.dto.MemberDTO;

import lombok.Data;

@Data
public class AppendDTO {
	
	private Long appendNo;
	private String appendName;
	private String appendType;
	private String appendInhName;
	private String appendSort;
//	private InquiryDTO refinquiryNo;
//	private CertifiedDTO refcertified_Id;
	private Long refnoticeNo;
	private MemberDTO member;
	/* 이미지 등록시 필요없어서 일단은 주석*/
//	private Long memberNo;
	private String appendStatus;
	private String appendPath;
	private String appendThumbnailPath;
	
}
