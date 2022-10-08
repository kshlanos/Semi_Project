package com.semi.project.mypage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semi.project.login.controller.LoginController;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mypage")
public class MypageController {
	
	@GetMapping("/infomodify")
	public String getinfoModify() {
		
		return "mypage/infomodify";
	}
	
	
	@GetMapping("/passwordchange")
	public String getpasswordChange() {
		
		return "mypage/passwordchange";
	}
	
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
