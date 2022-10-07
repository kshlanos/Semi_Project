package com.semi.project.study.todo.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.TodoList;
import com.semi.project.study.todo.repository.TodoListRepository;

@Service
@Transactional
public class TodoListService {
	
	private final TodoListRepository todoListRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public TodoListService(TodoListRepository todoListRepository, ModelMapper modelMapper) {
		this.todoListRepository = todoListRepository;
		this.modelMapper = modelMapper;
		
	}

	public TodoListDTO selectTodoList(Date todoListStartDate) {

		List<TodoList> TodoList = todoListRepository.findBytodoListStartDate(todoListStartDate);
		
		return modelMapper.map(TodoList, TodoListDTO.class);
	}

}
