package com.semi.project.study.detail.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudyMemberDTO {

	private String StudyRole;
	private String StudyId;
	private String StudyMemberId;
	private Date studyStartDate;
	private Date studyEndDate;
	private String studyMemStatus;
	private Long memberNo;
}
