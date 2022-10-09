package com.semi.project.study.todo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_CERTIFIED")
public class Certified {

	@Id
	@Column(name="TODOLIST_ID")
	private String todoListId;
	
	@Column(name="CERTIFIED_EXPLAIN")
	private String certifiedExplain;
	
	@Column(name="CERTIFIED_REG_STATUS")
	private String certifiedRegStatus;
	
}
