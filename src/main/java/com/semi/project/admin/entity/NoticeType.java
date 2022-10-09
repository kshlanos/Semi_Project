package com.semi.project.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_NOTICE_TYPE")
@Getter
@Setter
@NoArgsConstructor
public class NoticeType {
	
	@Id
	@Column(name = "NOTICE_CODE")	
	private Long noticeCode;
	
	@Column(name = "NOTICE_TYPE")	
	private String noticeType;
	
}
