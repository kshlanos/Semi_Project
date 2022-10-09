package com.semi.project.board.dto;

import java.sql.Date;

import com.semi.project.admin.dto.NoticeDTO;
import com.semi.project.login.dto.MemberDTO;

import lombok.Data;

@Data
public class CommentDTO {

	private Long commentNo;
	private String commentContent;
	private Date commentDate;
	private MemberDTO writer; //memberId
	/* MemberDTO 참조하여 MEMBER_ID 속성 작성 */
	/* InquiryDTO 참조하여 INQUIRY_NO 속성 작성 */
	private NoticeDTO noticeNo;
	private String commentStatus;
}
