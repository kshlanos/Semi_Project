package com.semi.project.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.service.BoardService;
import com.semi.project.common.Pagenation;
import com.semi.project.common.PagingButtonInfo;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/login/login")
	public String loginForm() {
		
		return "redirect:/login/login";
	}
	
	@GetMapping("/login/register")
	public String registerForm() {
		
		return "redirect:/login/register";
	}
	
	/* 스터디찾기 게시판 */
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
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
		
		log.info("[BoardController] ========================================= ");

		
		return "board/studyList";
	}
	
	/* 게시글 상세보기 */
	@GetMapping("detailMember")
	public String selectDetailMember(Model model, Long studyBoardNo) {
		
		log.info("[BoardController] ========================================= ");
		log.info("[BoardController] studyBoardNo : {}", studyBoardNo);
		
		BoardDTO board = boardService.selectDetailMember(studyBoardNo);
		
		model.addAttribute("board", board);
		
		
		return "board/detailMember";
	}

	
	/* 글 작성하기 */
	@GetMapping("studyWrite")
	public String registBoard() {
		
		return "board/studyWrite";
	}
	
	@PostMapping("studyWrite")
	public String registBoard(BoardDTO board, @AuthenticationPrincipal MemberDTO member) {
		
		log.info("[BoardController] ========================================= ");
		log.info("[BoardController] registBoard request : {}", board);
		
		board.setBoardWriter(member);
		boardService.registBoard(board);
//		board.setMemberNo(member);
//		boardService.registBoard(board);
				
		return "redirect:/board/studyList";
	}
	
	
	/* 글 삭제기능 */

	
	
	@PostMapping(value="/studyList")
	public String redirectMain() {
		
		return "redirect:/";
	}
}
