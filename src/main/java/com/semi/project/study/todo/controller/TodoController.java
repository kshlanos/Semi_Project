package com.semi.project.study.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/study")
public class TodoController {
	
	@GetMapping("/todoList")
	public void todoList() {}
	
}
