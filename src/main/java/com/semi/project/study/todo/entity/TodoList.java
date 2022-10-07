package com.semi.project.study.todo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", sequenceName = "SEQ_TODOLIST_ID")
public class TodoList {
	
	@Id
	private String todoListId;
	
	@Column
	private Date todoListStartDate;
	
	@Column
	private Date todoListEndDate;
	
	@Column
	private String todoListRegDate;
	
	@Column
	private Date todoListUpdDate;
	
	@Column
	private Date todoList_DEL_STATUS;
	
	@Column
	private String todoListRefId;
	

}
