package com.semi.project.board.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.semi.project.board.dto.AppliyDTO;
import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.service.BoardService;
import com.semi.project.common.Pagenation;
import com.semi.project.common.PagingButtonInfo;
import com.semi.project.login.dto.MemberDTO;

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
	public String selectDetailMember(Model model, Long studyId) {
		
		log.info("[BoardController] ========================================= ");
		log.info("[BoardController] studyId : {}", studyId);
		
		BoardDTO board = boardService.selectDetailMember(studyId);
		
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
	
	
	@PostMapping("/reqbtn")
	public ResponseEntity<List<AppliyDTO>> reqbtn(@RequestBody AppliyDTO reqbtn,
			@AuthenticationPrincipal MemberDTO member){
		
		log.info("[BoardController] ========================================= ");
		log.info("[BoardController] regbtn : {}", reqbtn);
		
		// 로그인 유저를 신청자로 설정 
		reqbtn.setAppliyId(member);
		
		// 신청 후 업데이트 된 신청자 목록 조회해서 반환 
		List<AppliyDTO> reqList = boardService.reqbtn(reqbtn);
		
		log.info("[BoardController] reqList : {}", reqList);
		log.info("[BoardController] ========================================= ");
		
		return ResponseEntity.ok(reqList);
	}
	
	

	
}