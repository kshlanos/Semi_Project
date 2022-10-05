package com.semi.project.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class BoardController {
	
	@GetMapping("/studylist")
	public String studyList() {
		
		return "board/studyList";
	}

	@GetMapping("/studyWrite")
	public String studyWrite() {
		
		return "board/studyWrite";
	}
}
