package com.semi.project.board.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.semi.project.login.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_STUDY_APPLIY")
@SequenceGenerator(name = "APPLIYNO_SEQ_GENERATOR", sequenceName = "SEQ_APPLIY_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Appliy implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPLIYNO_SEQ_GENERATOR")
	@Column(name = "APPLIY_NO")
	private Long appliyNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member appliyId;
	
	@Column(name = "APPLIY_STATUS")
	private String appliyStatus;
	
	@Column(name = "APPLIY_DESC")
	private String appliyDesc;
	
	@Column(name = "APPLIY_DATE")
	private Date appliyDate;
	
	@Column(name = "STUDY_PLAN_ID")
	private String studyPlanId;
	
	@Column(name = "REF_STUDY_ID")
	private Long refStudyId;
	
}
