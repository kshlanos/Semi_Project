package com.semi.project.study.detail.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_STUDY")
@SequenceGenerator(name = "STUDY_SEQ_GENERATOR", sequenceName = "SEQ_STUDY_NO",
initialValue = 1, 
allocationSize = 1
)
public class Study {
	
	@Id
	@Column(name="STUDY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDY_SEQ_GENERATOR")
	private String studyId;
	
	@Column(name="STUDY_TAG")
	private String studyTag;
	
	@Column(name="CLOSING_DATE")
	private Date closingDate;
	
	@Column(name="TARGET_DAY")
	private Date targetDay;
	
	@Column(name="RECRUITS")
	private Long recruits;
	
	@Column(name="STUDY_PLACE")
	private String studyPlace;
	
	@Column(name="STUDY_CONTENT")
	private String studyContent;
	
	@Column(name="STUDY_CATEGORY")
	private String studyCategory;
	
	@Column(name="STUDY_START_DATE")
	private Date studyStartDate;
	
	@Column(name="STUDY_END_DATE")
	private Date studyEndDate;
	
	@Column(name="STUDY_REG_DATE")
	private Date studyRegDate;
	
	@Column(name="STUDY_UPD_DATE")
	private Date studyUpdDate;
	
	@Column(name="STUDY_STATUS")
	private String studyStatus;
	
	@Column(name="STUDY_REF_ID")
	private String studyRefId;
	
	@Column(name="STUDY_BOARD_NO")
	private String studyBoardNo;
	
	@Column(name="MEMBER_NO")
	private Long memberNo;
	
	@Column(name="STUDY_CONDITION")
	private String studyCondition;
	
	@Column(name="STUDY_TITLE")
	private String studyTitle;

}
