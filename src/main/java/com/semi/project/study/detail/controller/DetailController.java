package com.semi.project.study.detail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class DetailController {

	@GetMapping("/confirm")
	public void confirm() {}
	
	@GetMapping("/mycalendar")
	public void mycalendar() {}
	
	@GetMapping("/studycalendar")
	public void studycalendar() {}
	
	@GetMapping("/certified")
	public void certified() {}
	
	@GetMapping("/stopwatch")
	public void stopwatch() {}
	
	
	
}
