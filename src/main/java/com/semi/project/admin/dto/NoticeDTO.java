package com.semi.project.admin.dto;

import java.sql.Date;

import com.semi.project.login.dto.MemberDTO;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Long noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeRegDate;
	private Date eventStart; /* 이벤트 날짜이므로 String으로 변경해야 할수 있음 */
	private Date eventEnd;   /* 이하동일 */
	private NoticeTypeDTO noticeCode;
	private MemberDTO noticeWriter;
	private String noticeDivision;
	private Date noticeUpdDate;
	private String noticeDelete;
	private int noticeViews;
	
}
