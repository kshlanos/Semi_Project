package com.semi.project.study.todo.dto;

import lombok.Data;

@Data
public class StopwatchCertifiedDTO {
	
	private String stopwatchId;
	private String todoListId;
	private Long memberId;
	private String stopwatchStatus;
	private Long remainTime;


}
