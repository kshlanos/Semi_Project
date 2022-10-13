package com.semi.project.study.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.semi.project.study.todo.dto.StopwatchCertifiedDTO;
import com.semi.project.study.todo.entity.StopwatchCertified;
import com.semi.project.study.todo.entity.TodoList;
import com.semi.project.study.todo.repository.StopwatchCertifiedRepository;

@Service
@Transactional
public class StopwatchCertifiedService {
	
	private final ModelMapper modelMapper;
	private final StopwatchCertifiedRepository stopwatchCertifiedRepository;
	
	public StopwatchCertifiedService (ModelMapper modelMapper, StopwatchCertifiedRepository stopwatchCertifiedRepository) {
		this.modelMapper = modelMapper;
		this.stopwatchCertifiedRepository = stopwatchCertifiedRepository;
	}

	public List<StopwatchCertifiedDTO> selectTime(String todoListId, Long memberNo) {

		List<StopwatchCertified> stopwatchCertified = stopwatchCertifiedRepository.findByTodoListIdAndMemberNo(todoListId, memberNo);
		
		return stopwatchCertified.stream().map(stopwatch -> modelMapper.map(stopwatch, StopwatchCertifiedDTO.class)).collect(Collectors.toList());
	}

	public void playStopwatch(Long remainTime, String todoListId, Long memberNo) {

		StopwatchCertified stopwatch = stopwatchCertifiedRepository.findRemainTimeByMemberNoAndTodoListId(memberNo, todoListId);
		stopwatch.setRemainTime(remainTime);
		
	}

}
