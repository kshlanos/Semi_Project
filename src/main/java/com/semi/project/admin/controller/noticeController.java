package com.semi.project.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class noticeController {
	
	@GetMapping("/noticeMain")
	public String getNoticeMain() {
		
		return "admin/noticeMain";
	}
	
	@GetMapping(value = "/noticeMainDetail")
	public String getNoticeMainDetail() {
		
		return "admin/noticeMainDetail";
	}

	@GetMapping(value = "/registNoticeMain")
	public String registNoticeMain() {
		
		return "admin/registNoticeMain";
	}
	
	@GetMapping(value = {"/noticeEvent"})
	public String getNoticeEvent() {
		return "admin/noticeEvent";
	}
	
	@GetMapping(value = {"/userListAdmin"})
	public String getUserListAdmin() {
		return "admin/userListAdmin";
	}

	@GetMapping(value = {"/userQnaListAdmin"})
	public String getUserQnaAdmin() {
		return "/admin/userQnaListAdmin";
	}
	
	@PostMapping(value="/noticeMain")  
	public String redirectMain() {
		
		return "redirect:/admin";
	}
	
}
