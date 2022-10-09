package com.semi.project.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/studyList")
	public String studyList() {
		
		return "board/studyList";
	}

	@GetMapping("studyWrite")
	public String studyWrite() {
		
		return "board/studyWrite";
	}
	

}
