package com.semi.project.board.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_STUDY_CATEGORY_TYPE")
public class Category {
	
	@Id	
	@Column(name = "STUDY_CATEGORY")
	private String studyCategory;
		
	@Column(name = "STUDY_CA_NAME")
	private String studyCaName;
	
	@Column(name = "STUDY_REF_ID")
	private String studyRefId;
	
}
