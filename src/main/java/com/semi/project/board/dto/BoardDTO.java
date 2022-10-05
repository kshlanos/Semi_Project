package com.semi.project.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {

	private String StudyId;
	private String StudyTag;
	private Date ClosingDate;
	private Date TargetDay;
	private Long Recruits;
	private String StudyPlace;
	private String StudyContent;
	private String StudyCategory;
	private Date StudyStartDate;
	private Date StudyEndDate;
	private String MemberId;
	private Date StudyRegDate;
	private Date StudyUpdDate;
	private char StudyStatus;
	private String StudyRefId;
	private String StudyBoardNo;
	
}
