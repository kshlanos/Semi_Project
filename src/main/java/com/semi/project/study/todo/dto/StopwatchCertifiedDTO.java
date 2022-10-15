package com.semi.project.study.todo.dto;

import lombok.Data;

@Data
public class StopwatchCertifiedDTO {
	
	private Long stopwatchId;
	private String todoListId;
	private Long memberNo;
	private String stopwatchStatus;
	private Long remainTime;


}
