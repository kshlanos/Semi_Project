package com.semi.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	/* 마이페이지 메인 이동*/
	@GetMapping("/")
	public String getmypageMain() {
		
		return "mypage/infomodify";
	}
	
	/* 회원정보 페이지로 이동*/
	@GetMapping("/infomodify")
	public String getinfoModify() {
		
		return "mypage/infomodify";
	}
	
	/* 비밀번호변경 페이지로 이동*/
	@GetMapping("/passwordchange")
	public String getpasswordChange() {
		
		return "mypage/passwordchange";
	}
	
	/* 비밀번호 확인페이지 이동 */
	@GetMapping("/passwordcheck")
	public String getpasswordCheck() {
		
		return "mypage/passwordcheck";
	}
	
	@GetMapping("/mystudy")
	public String getmyStudy() {
		
		return "mypage/mystudy";
	}
	
	@GetMapping("/myboard")
	public String getmyboard() {
		
		return "mypage/myboard";
	}
	
	@GetMapping("/inquirywrite")
	public String getwriteInquiry() {
		
		return "mypage/inquirywrite";
	}
	
	@GetMapping("/inquirymodify")
	public String getinquiryModify() {
		
		return "mypage/inquirymodify";
	}
	
	@GetMapping("/inquirycheck")
	public String getinquiryCheck() {
		
		return "mypage/inquirycheck";
	}
	
	@GetMapping("/noticecheck")
	public String getnoticeCheck() {
		
		return "mypage/noticecheck";
	}
	
	@GetMapping("/inquiryconfirm")
	public String getinquiryConfirm() {
		
		return "mypage/inquiryconfirm";
	}
	
	@GetMapping("/inforemove")
	public String infoRemove() {
		
		return "mypage/inforemove";
	}
	
}
