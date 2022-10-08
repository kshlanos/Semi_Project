package com.semi.project.study.todo.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.TodoList;
import com.semi.project.study.todo.repository.TodoListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	public List<TodoListDTO> selectTodoList(Date todoListStartDate) {

        
		List<TodoList> todoList = todoListRepository.findAllBytodoListStartDate(todoListStartDate);
		
		log.info("[TodoService] todoListStartDate : {}",todoListStartDate);
		log.info("[TodoService] todoListStartDate : {}",todoList);
		
		return todoList.stream().map(todo -> modelMapper.map(todo, TodoListDTO.class)).collect(Collectors.toList());
	}

}
