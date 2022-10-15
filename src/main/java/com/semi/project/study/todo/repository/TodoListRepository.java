package com.semi.project.study.todo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semi.project.study.todo.entity.Certified;
import com.semi.project.study.todo.entity.Stopwatch;
import com.semi.project.study.todo.entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList, Integer>{

	@Query("SELECT t "
			+ "FROM TodoList t "
			+ "WHERE todoListStartDate <= :todoDate "
			+ "and todoListEndDate >= :todoDate "
			+ "and studyId = :studyId")
	List<TodoList> findAllBytodoListStartDateAndStudyId(@Param("todoDate") Date todoListStartDate, @Param("studyId") Long studyId);

	TodoList findBytodoListId(String todoListId);

	List<TodoList> findStopwatchBytodoListId(String todoListId);

	TodoList findStudyIdByTodoListId(String todoListId);
	
}
