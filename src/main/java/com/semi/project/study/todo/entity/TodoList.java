package com.semi.project.study.todo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "TBL_STUDY_DATE")
@SequenceGenerator(name = "TODOLIST_SEQ_GENERATOR", sequenceName = "SEQ_TODOLIST_ID",
initialValue = 1, 
allocationSize = 1
)
public class TodoList {
	
	@Id
	@Column(name="TODOLIST_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODOLIST_SEQ_GENERATOR")
	private String todoListId;

	@Column(name="TODOLIST_START_DATE")
	private Date todoListStartDate;
	
	@Column(name="STUDY_ID")
	private Long studyId;
	
	@Column(name="TODOLIST_END_DATE")
	private Date todoListEndDate;
	
	@Column(name="TODOLIST_SORT")
	private String todoListSort;
	
	@Column(name="TODOLIST_CRE_DATE")
	private Date todoListCreDate;
	
	@Column(name="TODOLIST_UPD_DATE")
	private Date todoListUpdDate;
	
	@Column(name="TODOLIST_DEL_STATUS")
	private String todoListDelStatus;
	
	@Column(name="TODOLIST_REF_ID")
	private String todoListRefId;
	
	@OneToOne
	@JoinColumn(name = "TODOLIST_ID")
	private Certified certified;
	
	@OneToOne
	@JoinColumn(name = "TODOLIST_ID")
	private Stopwatch stopwatch;
		
}
