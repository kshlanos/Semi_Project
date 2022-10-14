package com.semi.project.board.dto;

import java.sql.Date;

import com.semi.project.admin.dto.NoticeDTO;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.mypage.dto.InquiryDTO;

import lombok.Data;

@Data
public class CommentDTO {

	private Long commentNo;
	private String commentContent;
	private Date commentRegDate;
	private InquiryDTO refInquiry;
	private NoticeDTO refNotice;
	private String commentStatus;
	private MemberDTO commentWriter;
}
