package com.semi.project.study.todo.dto;

import java.sql.Date;

import com.semi.project.study.todo.entity.Certified;
import com.semi.project.study.todo.entity.Stopwatch;

import lombok.Data;

@Data
public class TodoListDTO {

	private String todoListId;
	private Date todoListStartDate;
	private String studyId;
	private Date todoListEndDate;
	private String todoListSort;
	private Date todoListCreDate;
	private Date todoListUpdDate;
	private String todoListDelStatus;
	private String todoListRefId;
	private Certified certified;
	private Stopwatch stopwatch;
}
