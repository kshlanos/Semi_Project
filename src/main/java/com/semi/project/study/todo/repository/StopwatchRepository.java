package com.semi.project.study.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.study.todo.entity.Stopwatch;

public interface StopwatchRepository extends JpaRepository<Stopwatch, Integer>{

}
