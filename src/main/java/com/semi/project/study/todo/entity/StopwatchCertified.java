package com.semi.project.study.todo.entity;

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
@Table(name = "TBL_STOPWATCH_CERTIFIED")
@SequenceGenerator(name = "STOPWATCH_SEQ_GENERATOR", sequenceName = "SEQ_STOPWATCH_ID",
initialValue = 1, 
allocationSize = 1
)
public class StopwatchCertified {
	
	@Id
	@Column(name="STOPWATCH_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOPWATCH_SEQ_GENERATOR")
	private Long stopwatchId;
	
	@Column(name="TODOLIST_ID")
	private String todoListId;
	
	@Column(name="MEMBER_NO")
	private Long memberNo;
	
	@Column(name="STOPWATCH_STATUS")
	private String stopwatchStatus;
	
	@Column(name="REMAIN_TIME")
	private Long remainTime;

}
