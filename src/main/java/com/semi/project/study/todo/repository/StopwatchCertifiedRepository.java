package com.semi.project.study.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.study.todo.entity.StopwatchCertified;
 
public interface StopwatchCertifiedRepository extends JpaRepository<StopwatchCertified, Integer>{

	List<StopwatchCertified> findByTodoListIdAndMemberNo(String todoListId, Long memberNo);

	StopwatchCertified findRemainTimeByMemberNoAndTodoListId(Long memberNo, String todoListId);



}
