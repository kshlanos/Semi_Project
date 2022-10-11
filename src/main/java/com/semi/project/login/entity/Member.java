package com.semi.project.login.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_MEMBER")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(
		name = "MEMBER_SEQ_GENERATOR",
		sequenceName = "SEQ_MEMBER_NO",
		initialValue = 1,
		allocationSize = 1
)
@DynamicInsert 
public class Member {

	@Id
	@Column(name = "MEMBER_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "MEMBER_SEQ_GENERATOR"
	)
	private long memberNo;
	
	@Column(name = "MEMBER_ID")
	private String memberId;
	
	@Column(name = "MEMBER_PWD")
	private String memberPwd;	
	
	@Column(name = "MEMBER_NAME")
	private String memberName;		
	
	@Column(name = "MEMBER_GENDER")
	private String memberGender;	
	
	@Column(name = "MEMBER_NICKNAME")
	private String memberNickname;			
	
	@Column(name = "MEMBER_BIRTH")
	private Date memberbirth;			
	
	@Column(name = "MEMBER_ADDRESS")
	private String memberAddress;	

	@Column(name = "MEMBER_EMAIL")
	private String memberEmail;		
	
	@Column(name = "MEMBER_RATING")
	private String memberRating;			
	
	@Column(name = "MEMBER_STATUS")
	private String memberStatus;				
	
	@Column(name = "MEMBER_DELETE_DATE")
	private Date memberDeletedate;		
	
	@Column(name = "MEMBER_ROLE")
	private String memberRole;		

	@Column(name = "MEMBER_RATING_COUNT")
	private long memberRatingCount;		
	
	@Column(name = "MEMBER_PHONE")
	private long memberPhone;		
	
	@Column(name = "MEMBER_GRADE")
	private String memberGrade;
	
	
}
