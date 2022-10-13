package com.semi.project.study.todo.controller;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.project.login.dto.CustomUser;
import com.semi.project.study.detail.service.StudyMemberService;
import com.semi.project.study.todo.dto.StopwatchCertifiedDTO;
import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.service.StopwatchCertifiedService;
import com.semi.project.study.todo.service.TodoListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/study")
public class TodoController {
	
	private final TodoListService todoListService;
	private final ModelMapper modelMapper;
	private final StudyMemberService studyMemberService;
	private final StopwatchCertifiedService stopwatchCertifiedService;
	
	public TodoController(TodoListService todoListService, ModelMapper modelMapper, StudyMemberService studyMemberService, StopwatchCertifiedService stopwatchCertifiedService) {
		this.todoListService = todoListService;
		this.modelMapper = modelMapper;
		this.studyMemberService = studyMemberService;
		this.stopwatchCertifiedService = stopwatchCertifiedService;
	}

//	@GetMapping("/todoList")
//	public String todoList() {
//		
//		return "/study/todoList";;;
//	}
	
	@GetMapping("/todoList")
	public String selectTodoList(Model model, String todoListStartDate, int studyNo, @AuthenticationPrincipal CustomUser user) {
		
		log.info("[TodoController] todoListStartDate : {}", todoListStartDate);
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate date = LocalDate.parse(todoListStartDate, formatter);
		java.sql.Date date = java.sql.Date.valueOf(todoListStartDate);
		
		List<TodoListDTO> todoList = todoListService.selectTodoList(date, studyNo, user.getMemberNo());

		TodoListDTO todoListDTO = todoList.get(0);
		
		model.addAttribute("todoList", todoListDTO);
		
		log.info("[TodoController] todoListDate : {}", todoList);
		
		return "/study/todoList";
		
	}
	
	@GetMapping("/todoModify")
	public String todoModify(Model model, String todoListStartDate, int studyNo, @AuthenticationPrincipal CustomUser user) {
		
		log.info("[TodoController Modify] todoListStartDate : {}", todoListStartDate);
		
		java.sql.Date date = java.sql.Date.valueOf(todoListStartDate);
		
		List<TodoListDTO> todoList = todoListService.selectTodoList(date, studyNo, user.getMemberNo());

		TodoListDTO todoListDTO = todoList.get(0);
		
		model.addAttribute("todoList", todoListDTO);
		
		log.info("[TodoController] todoListDate : {}", todoList);
		
		return "/study/todoModify";
	}
	
	@PostMapping("/todoModify")
	public String todoListUpdate (Model model, String certifiedExplain, Long stopwatchTime, String todoListId) {
		
		
		todoListService.modifyTodoList(certifiedExplain, stopwatchTime, todoListId);

		
		return "/study/close";
	}
	
	@GetMapping("/stopwatch")
	public String stopwatch(Model model, String todoListId, @AuthenticationPrincipal CustomUser user) {
		
		log.info("[TodoController Stopwatch] todoListId : {}", todoListId);
		
		List<StopwatchCertifiedDTO> stopwatch = stopwatchCertifiedService.selectTime(todoListId, user.getMemberNo());
		
		log.info("[stopwatch] : {}", stopwatch);
		
		StopwatchCertifiedDTO Time = stopwatch.get(0);
		
		model.addAttribute("stopwatch", Time);
		
		log.info("[TodoController Stopwatch] todoList : {}", Time);
		
		return "/study/stopwatch";
	}
	
	@PostMapping("/stopwatch")
	public String PlayStopwatch(Model model, Long remainTime, String todoListId, @AuthenticationPrincipal CustomUser user) {
		
		stopwatchCertifiedService.playStopwatch(remainTime, todoListId, user.getMemberNo());
		
		return "/study/close";
	}
	
	
}
