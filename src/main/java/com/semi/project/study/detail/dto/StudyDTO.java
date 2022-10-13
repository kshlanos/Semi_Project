package com.semi.project.study.detail.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudyDTO {

	private String studyId;
	private String studyTag;
	private Date closingDate;
	private Date targetDay;
	private Long recruits;
	private String studyPlace;
	private String studyContent;
	private String studyCategory;
	private Date studyStartDate;
	private Date studyEndDate;
	private Date studyRegDate;
	private Date studyUpdDate;
	private String studyStatus;
	private String studyRefId;
	private String studyBoardNo;
	private Long memberNo;
	private String studyCondition;
	private String studyTitle;
}
