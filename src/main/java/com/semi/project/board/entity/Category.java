package com.semi.project.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
	
	@Column(name = "REF_ID")
	private String refId;
	
}
