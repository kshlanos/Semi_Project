package com.semi.project.study.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.study.todo.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Long>{

}
