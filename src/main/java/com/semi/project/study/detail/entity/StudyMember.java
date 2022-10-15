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
@Table(name = "TBL_STUDYMEMBER")
@SequenceGenerator(name = "STUDYMEMBER_SEQ_GENERATOR", sequenceName = "SEQ_STUDYMEMBER_ID",
initialValue = 1, 
allocationSize = 1
)
public class StudyMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDYMEMBER_SEQ_GENERATOR")
	@Column(name="STUDY_MEM_ID")
	private String studyMemberId;
	
	@Column(name="STUDY_ROLE")
	private String studyRole;
	
	@Column(name="STUDY_ID")
	private Long studyId;
	
	@Column(name="STUDY_START_DATE")
	private Date studyStartDate;
	
	@Column(name="STUDY_END_DATE")
	private Date studyEndDate;
	
	@Column(name="STUDY_MEM_STATUS")
	private String studyMemStatus;
	
	@Column(name="MEMBER_NO")
	private Long memberNo;
}
