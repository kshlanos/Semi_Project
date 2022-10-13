package com.semi.project.board.dto;

import java.sql.Date;

import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;

import lombok.Data;

@Data
public class BoardDTO {

	private String studyId;
	private String studyTag;
	private Date closingDate;
	private Date targetDay;
	private Long recruits;
	private String studyPlace;
	private String studyContent;
	private CategoryDTO studyCategory;
	private Date studyStartDate;
	private Date studyEndDate;
	private Date studyRegDate;
	private Date studyUpdDate;
	private String studyStatus;
	private String studyRefId;
	private Long studyBoardNo;
	private MemberDTO boardWriter;
	private String studyCondition;
	private String studyTitle;
	private String studyName;
	private Long boardCount;
	
}

