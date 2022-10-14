package com.semi.project.admin.dto;

import java.sql.Date;
import java.util.List;

import com.semi.project.board.dto.AppendDTO;
import com.semi.project.login.dto.MemberDTO;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private Long noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeRegDate;
	private Date eventStart; 
	private Date eventEnd;  
	private NoticeTypeDTO noticeCode;
	private MemberDTO noticeWriter;
	private String noticeDivision;
	private Date noticeUpdDate;
	private String noticeDelete;
	private int noticeViews;
	private String noticeEventContent;
	private List<AppendDTO> noticeAppendFileList;

	
}
