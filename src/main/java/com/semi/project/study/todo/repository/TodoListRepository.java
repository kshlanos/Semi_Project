package com.semi.project.study.todo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.study.todo.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Integer>{

	List<TodoList> findBytodoListStartDate(Date todoListStartDate);
}
