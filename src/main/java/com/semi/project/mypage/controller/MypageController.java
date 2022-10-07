package com.semi.project.mypage.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semi.project.login.dto.MemberDTO;
import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.service.InquiryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	 private final InquiryService inquiryService;
	 private final MessageSourceAccessor messageSourceAccesor;
	 
	 public MypageController(InquiryService inquiryService, MessageSourceAccessor messageSourceAccesor) {
		 this.inquiryService = inquiryService;
		 this.messageSourceAccesor = messageSourceAccesor;
	 }
	
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
	
	@PostMapping("/inquirywrite")
	public String inquiryBoard(InquiryDTO inquiry, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("[MypageController] ==========");
		log.info("[MypageController] inquiryBoard request : {}", inquiry);
		log.info("[MypageController] inquiryBoard request : {}", member);
		
		inquiry.setMember(member);
		inquiryService.inquiryBoard(inquiry);
		
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("mypage.inquirywrite"));
		
		return "redirect:/mypage/inquirycheck";
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
