package com.semi.project.study.todo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.project.study.detail.entity.StudyMember;
import com.semi.project.study.detail.repository.StudyMemberRepository;
import com.semi.project.study.todo.dto.StopwatchCertifiedDTO;
import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.StopwatchCertified;
import com.semi.project.study.todo.entity.TodoList;
import com.semi.project.study.todo.repository.StopwatchCertifiedRepository;
import com.semi.project.study.todo.repository.TodoListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StopwatchCertifiedService {
	
	private final ModelMapper modelMapper;
	private final StopwatchCertifiedRepository stopwatchCertifiedRepository;
	private final TodoListRepository todoListRepository;
	private final StudyMemberRepository studyMemberRepository;
	
	@Autowired
	public StopwatchCertifiedService (ModelMapper modelMapper, StopwatchCertifiedRepository stopwatchCertifiedRepository,
			StopwatchService stopwatchService, TodoListRepository todoListRepository, StudyMemberRepository studyMemberRepository) {
		this.modelMapper = modelMapper;
		this.stopwatchCertifiedRepository = stopwatchCertifiedRepository;
		this.todoListRepository = todoListRepository;
		this.studyMemberRepository = studyMemberRepository;
	}

	public List<StopwatchCertifiedDTO> selectTime(String todoListId, Long memberNo) {

		List<StopwatchCertified> stopwatchCertified = stopwatchCertifiedRepository.findByTodoListIdAndMemberNo(todoListId, memberNo);
		
		return stopwatchCertified.stream().map(stopwatch -> modelMapper.map(stopwatch, StopwatchCertifiedDTO.class)).collect(Collectors.toList());
	}

	public void playStopwatch(Long remainTime, String todoListId, Long memberNo) {
		
		StopwatchCertified stopwatch = stopwatchCertifiedRepository.findRemainTimeByMemberNoAndTodoListId(memberNo, todoListId);

		log.info("[TodoService Stopwatch] stopwatch : {}", stopwatch);
		
		if(remainTime == 0) {
			stopwatch.setRemainTime(remainTime);
			stopwatch.setStopwatchStatus("Y");
		} else {
			stopwatch.setRemainTime(remainTime);			
		}
		
		log.info("[TodoService Stopwatch] stopwatch : {}", stopwatch);
		
	}

	public void registStopwatch(TodoListDTO todoList) {
		
		TodoList studyId = todoListRepository.findStudyIdByTodoListId(todoList.getTodoListId());
		List<StudyMember> studyMemberList = studyMemberRepository.findByStudyId(studyId.getStudyId());
		StopwatchCertifiedDTO newStopwatch = new StopwatchCertifiedDTO();
		
		for ( StudyMember study : studyMemberList ) {
				
			newStopwatch.setMemberNo(study.getMemberNo());
			newStopwatch.setRemainTime(3600L);
			newStopwatch.setStopwatchStatus("N");
			newStopwatch.setTodoListId(todoList.getTodoListId());
			stopwatchCertifiedRepository.save(modelMapper.map(newStopwatch, StopwatchCertified.class));
			
			log.info("[TodoController regi studyMemberList] newStopwatch : {}", newStopwatch);
		}
		
		
//		
//		
//		newStopwatch.setMemberId(null);
//		newStopwatch.setRemainTime(null);
//		newStopwatch.setStopwatchId(null);
//		newStopwatch.setStopwatchStatus(null);
//		newStopwatch.setTodoListId(null);
//		stopwatchCertifiedRepository.save(modelMapper.map(newStopwatch, StopwatchCertified.class));
		
	}

}
