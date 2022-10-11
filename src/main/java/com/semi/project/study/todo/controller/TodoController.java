package com.semi.project.study.todo.controller;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.Stopwatch;
import com.semi.project.study.todo.service.TodoListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/study")
public class TodoController {
	
	private final TodoListService todoListService;
	private final ModelMapper modelMapper;
	
	public TodoController(TodoListService todoListService, ModelMapper modelMapper) {
		this.todoListService = todoListService;
		this.modelMapper = modelMapper;
	}

//	@GetMapping("/todoList")
//	public String todoList() {
//		
//		return "/study/todoList";
//	}
	
	@GetMapping("/todoList")
	public String selectTodoList(Model model, String todoListStartDate) {
		
		log.info("[TodoController] todoListStartDate : {}", todoListStartDate);
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //LocalDate date = LocalDate.parse(todoListStartDate, formatter);
		java.sql.Date date = java.sql.Date.valueOf(todoListStartDate);
		
		List<TodoListDTO> todoList = todoListService.selectTodoList(date);
		
		TodoListDTO todoListDate = todoList.get(0);

		model.addAttribute("todoList", todoListDate);
		
		log.info("[TodoController] todoListDate : {}", todoListDate);
		
		return "/study/todoList";
		
	}
	
	@GetMapping("/todoModify")
	public String todoModify(Model model, String todoListStartDate) {
		
		log.info("[TodoController Modify] todoListStartDate : {}", todoListStartDate);
		
		java.sql.Date date = java.sql.Date.valueOf(todoListStartDate);
		
		List<TodoListDTO> todoList = todoListService.selectTodoList(date);
		
		TodoListDTO todoListDate = todoList.get(0);

		model.addAttribute("todoList", todoListDate);
		
		log.info("[TodoController Modify] todoListDate : {}", todoListDate);
		
		return "/study/todoModify";
	}
	
	@PostMapping("/todoModify")
	public String todoListUpdate (Model model, String certifiedExplain, Long stopwatchTime, String todoListId) {
		
		
		todoListService.modifyTodoList(certifiedExplain, stopwatchTime, todoListId);

		
		return "redirect:/study/studyCalendar";
	}
	
	@GetMapping("/stopwatch")
	public void stopwatch(Model model, String todoListId) {
		
		log.info("[TodoController Stopwatch] todoListId : {}", todoListId);
		
		List<TodoListDTO> todoList = todoListService.selectStopwatch(todoListId);
		
		TodoListDTO todoListStopwatch = todoList.get(0);
		
		model.addAttribute("todoList", todoListStopwatch);
		
		log.info("[TodoController Stopwatch] todoList : {}", todoListStopwatch);
		
	}
	
	@PostMapping("/stopwatch")
	public String PlayStopwatch(Model model, Long stopwatchTime, String todoListId) {
		
		todoListService.playStopwatch(stopwatchTime, todoListId);
		
		return "redirect:/study/studyCalendar";
	}
	
}
