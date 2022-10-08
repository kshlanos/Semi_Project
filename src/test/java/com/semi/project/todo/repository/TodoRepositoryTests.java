package com.semi.project.todo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.semi.project.DobebestApplication;
import com.semi.project.study.todo.entity.TodoList;
import com.semi.project.study.todo.repository.TodoListRepository;

@SpringBootTest
@ContextConfiguration(classes = { DobebestApplication.class })
public class TodoRepositoryTests {
	
	@Autowired
	private TodoListRepository todoListRepository;
	
	@Test
	public void 날짜로_todoList_조회_테스트 () {
		
		//given
		Date date  = new Date();
		
		//when
		//List<TodoList> todoList = todoListRepository.findAllBytodoListStartDate(date);
		
		//then
//		System.out.println(todoList);
	}

}
