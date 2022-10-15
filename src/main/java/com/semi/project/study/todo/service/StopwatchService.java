package com.semi.project.study.todo.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.semi.project.study.todo.dto.StopwatchDTO;
import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.Stopwatch;
import com.semi.project.study.todo.repository.StopwatchRepository;

@Service
@Transactional
public class StopwatchService {

	private final ModelMapper modelMapper;
	private final StopwatchRepository stopwatchRepository;
	
	public StopwatchService (StopwatchRepository stopwatchRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.stopwatchRepository = stopwatchRepository;
	}
	
	public void registStopwatch(StopwatchDTO stopwatch) {
		
		stopwatchRepository.save(modelMapper.map(stopwatch, Stopwatch.class));
		
	}

}
