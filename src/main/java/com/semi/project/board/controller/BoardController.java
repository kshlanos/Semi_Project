package com.semi.project.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.service.BoardService;
import com.semi.project.common.Pagenation;
import com.semi.project.common.PagingButtonInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping("studyList")
	public String studyList(@RequestParam(defaultValue="1") int page,
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("[BoardController] ========================================= ");
		log.info("[BoardController] param page : {}", page);
		log.info("[BoardController] param searchValue : {}", searchValue);
		
		Page<BoardDTO> studyList = boardService.selectStudyList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(studyList);
		
		log.info("[BoardController] studyList : {}", studyList);
		log.info("[BoardController] studyList : {}", paging);
		
		model.addAttribute("studyList", studyList);
		model.addAttribute("paging", paging);
		
		log.info("[BoardController] ========================================= ");
		
		return "board/studyList";
	}

	@GetMapping("studyWrite")
	public String studyWrite() {
		
		return "board/studyWrite";
	}
	
	@PostMapping(value="/studyList")
	public String redirectMain() {
		
		return "redirect:/";
	}
}
