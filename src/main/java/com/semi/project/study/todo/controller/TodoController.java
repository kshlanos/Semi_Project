package com.semi.project.study.todo.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.service.TodoListService;

@Controller
@RequestMapping("/study")
public class TodoController {
	
	private final TodoListService todoListService;
	
	public TodoController(TodoListService todoListService) {
		this.todoListService = todoListService;
	}

//	@GetMapping("/todoList")
//	public String todoList() {
//		
//		return "/study/todoList";
//	}
	
	@GetMapping("/todoList")
	public String selectTodoList(Model model, Date todoListStartDate) {
		
		TodoListDTO board = todoListService.selectTodoList(todoListStartDate);
		
		model.addAttribute("todoList", board);
		
		return "/study/todoList";
		
	}
	
}
