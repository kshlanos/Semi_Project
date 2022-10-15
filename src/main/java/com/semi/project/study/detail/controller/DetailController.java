package com.semi.project.study.detail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class DetailController {

	@GetMapping("/confirm")
	public void confirm() {}
	
	@GetMapping("/myCalendar")
	public void myCalendar() {}
	
	@GetMapping("/studyCalendar")
	public void studyCalendar() {}
	
	@GetMapping("/certified")
	public void certified() {}

}
