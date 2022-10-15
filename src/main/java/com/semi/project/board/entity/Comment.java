package com.semi.project.board.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.semi.project.admin.entity.Notice;
import com.semi.project.login.entity.Member;
import com.semi.project.mypage.entity.Inquiry;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_COMMENT")
@SequenceGenerator(
		name = "COMMENT_SEQ_GENERATOR", 
		sequenceName = "SEQ_COMMENT_NO", 
		initialValue = 1, 
		allocationSize = 1
)
@DynamicInsert
public class Comment {
	
	@Id
	@Column(name = "COMMENT_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GENERATOR")
	private Long commentNo;
	
	@Column(name = "COMMENT_CONTENT")
	private String commentContent;
	
	@Column(name = "COMMENT_REG_DATE")
	private Date commentRegDate;
	
	@ManyToOne
	@JoinColumn(name = "REF_INQUIRY_NO")
	private Inquiry refInquiry;
	
	@ManyToOne
	@JoinColumn(name = "REF_NOTICE_NO")
	private Notice refNotice;
	
	@Column(name = "COMMENT_STATUS")
	private String commentStatus;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member commentWriter;
}
