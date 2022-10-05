package com.semi.project.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class noticeController {
	
	@GetMapping("noticeMain")
	public String getNoticeMain() {
		
		return "admin/noticeMain";
	}
	
	@GetMapping("noticeEvent")
	public String getNoticeEvent() {
		return "admin/noticeEvent";
	}
	
	@GetMapping("userListAdmin")
	public String getUserListAdmin() {
		return "admin/userListAdmin";
	}

	@GetMapping("userQnaListAdmin")
	public String getUserQnaAdmin() {
		return "admin/userQnaListAdmin";
	}
	
}
