package com.semi.project.board.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_APPEND")
@SequenceGenerator(
		name = "APPEND_SEQ_GENERATOR", 
		sequenceName = "SEQ_APPEND_NO", 
		initialValue = 1, 
		allocationSize = 1
)
@DynamicInsert
public class Append {
	
	@Id
	@Column(name = "APPEND_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPEND_SEQ_GENERATOR")
	private Long appendNo;
	
	@Column(name = "APPEND_NAME")
	private String appendName;

	@Column(name = "APPEND_TYPE")
	private String appendType;
	
	@Column(name = "APPEND_INH_NAME")
	private String appendInhName;
	
	@Column(name = "APPEND_SORT")
	private String appendSort;
	
	
//	private InquiryDTO refinquiryNo;
	
	
//	private CertifiedDTO refcertified_Id;
	
	@ManyToOne
	@JoinColumn(name = "REF_NOTICE_NO")
	private Notice refnoticeNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")
	private Member memberNo;
	
	@Column(name = "APPEND_STATUS")
	private String appendStatus;
	
	@Column(name = "APPEND_PATH")
	private String appendPath;
	
	@Column(name = "APPEND_THUMBNAIL_PATH")
	private String appendThumbnailPath;
	
	
}
