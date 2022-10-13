package com.semi.project.mypage.entity;

import java.sql.Date;

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

import com.semi.project.login.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_INQUIRY")
@SequenceGenerator(
			name = "INQUIRY_SEQ_GENERATOR",
			sequenceName = "SEQ_INQUIRY_NO",
			initialValue = 1,
			allocationSize = 1	
		)
@DynamicInsert 
public class Inquiry {
	
	@Id
	@Column(name = "INQUIRY_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, 
			generator = "INQUIRY_SEQ_GENERATOR"
	)
	private Long inquiryNo;
	
	@Column(name = "INQUIRY_TITLE")
	private String inquiryTitle;
	
	@Column(name = "INQUIRY_CONTENT")
	private String inquiryContent;
	
	@Column(name= "INQUIRY_REG_DATE")
	private Date inquiryRegDate;
	
	@Column(name= "INQUIRY_DELETE")
	private String inquiryDelete;
	
	@Column(name = "INQUIRY_MOD_DATE")
	private Date inquiryModDate;
	
	
	@Column(name = "INQUIRY_REF_NO")
	private Long inquiryRefNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member member;
	
	@Column(name = "INQUIRY_STATUS")
	private String inquiryStatus;
	
}
