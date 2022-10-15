package com.semi.project.board.dto;

import java.sql.Date;

import com.semi.project.login.dto.MemberDTO;


import lombok.Data;

@Data
public class BoardDTO {

	private Long studyId;
	private String studyTag;
	private Date closingDate;
	private String targetDay;
	private Long recruits;
	private String studyPlace;
	private String studyContent;
	private String studyCategory;
	//private CategoryDTO category;
	private Date studyStartDate;
	private Date studyEndDate;
	private Date studyRegDate;
	private Date studyUpdDate;
	private String studyStatus;
	private String studyRefId;
//	private Long studyBoardNo;
	private MemberDTO boardWriter;
	private String studyCondition;
	private String studyTitle;
	private String studyName;
	private Long boardCount;
	private String categoryName;
		
	}
	
	

