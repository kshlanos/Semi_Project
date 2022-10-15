package com.semi.project.board.dto;

import java.sql.Date;

import com.semi.project.login.dto.MemberDTO;

import lombok.Data;

@Data
public class AppliyDTO {

	private Long appliyNo;
	private MemberDTO appliyId;
	private String appliyStatus;
	private String appliyDesc;
	private Date appliyDate;
	private String studyPlanId;
	private Long refStudyId;
	
}
