package com.semi.project.study.detail.controller;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.service.BoardService;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.study.detail.dto.StudyMemberDTO;
import com.semi.project.study.detail.service.StudyMemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/study")
public class DetailController {
	
	private final BoardService boardService;
	private final StudyMemberService studyMemberService;
	
	public DetailController (BoardService boardService, StudyMemberService studyMemberService) {
		this.studyMemberService = studyMemberService;
		this.boardService = boardService;
	}

	@GetMapping("/confirm")
	public void confirm() {}
	
	@GetMapping("/myCalendar")
	public void myCalendar() {}
	
	@GetMapping("/studyCalendar")
	public String studyCalendar(Model model, int studyNo, @AuthenticationPrincipal MemberDTO member) {
		
		List<StudyMemberDTO> studyList = studyMemberService.selectAllStudy(member.getMemberNo());
		
		List<BoardDTO> study = boardService.selectBoard(studyList);
		BoardDTO studyInfo = study.get(studyNo);
		
		log.info("study: {}", studyInfo);
		
		model.addAttribute("study", studyInfo);
		
		return "/study/studyCalendar";
	}
	
	@GetMapping("/certified")
	public void certified() {}

}
