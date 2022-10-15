package com.semi.project.admin.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.semi.project.board.entity.Append;
import com.semi.project.login.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBL_NOTICE")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(
		name = "NOTICE_SEQ_GENERATOR",
		sequenceName = "SEQ_NOTICE_NO",
		initialValue = 5,
		allocationSize = 1
)
@DynamicInsert 
public class Notice {
	
	@Id
	@Column(name = "NOTICE_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "NOTICE_SEQ_GENERATOR"
	)
	private Long noticeNo;
	
	@Column(name = "NOTICE_TITLE")	
	private String noticeTitle;
	
	@Column(name = "NOTICE_CONTENT")	
	private String noticeContent;
	

	@Column(name = "NOTICE_REG_DATE")	
	private Date noticeRegDate;
	
	@Column(name = "EVENT_START")	
	private Date eventStart;
	
	@Column(name = "EVENT_END")	
	private Date eventEnd;
	
	@ManyToOne
	@JoinColumn(name = "NOTICE_CODE")		
	private NoticeType noticeCode;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_NO")	
	private Member noticeWriter;
	
	@Column(name = "NOTICE_DIVISION")	
	private String noticeDivision;
	
 
	@Column(name = "NOTICE_UPD_DATE")	
	private Date noticeUpdDate;
	
	@Column(name = "NOTICE_DELETE")	
	private String noticeDelete;
	
	@Column(name = "NOTICE_VIEWS")	
	private int noticeViews;
	
	@Column(name = "NOTICE_EVENT_CONTENT")
	private String noticeEventContent;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "REF_NOTICE_NO")
	private List<Append> noticeAppendFileList;
	
	
}
