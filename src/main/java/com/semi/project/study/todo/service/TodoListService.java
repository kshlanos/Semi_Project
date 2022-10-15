package com.semi.project.study.todo.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.service.BoardService;
import com.semi.project.study.detail.dto.StudyMemberDTO;
import com.semi.project.study.detail.repository.StudyMemberRepository;
import com.semi.project.study.detail.service.StudyMemberService;
import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.StopwatchCertified;
import com.semi.project.study.todo.entity.TodoList;
import com.semi.project.study.todo.repository.StopwatchCertifiedRepository;
import com.semi.project.study.todo.repository.TodoListRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TodoListService {
	
	private final TodoListRepository todoListRepository;
	private final StudyMemberService studyMemberService;
	private final ModelMapper modelMapper;
	private final BoardService boardService;
	private final StudyMemberRepository studyMemberRepository;
	private final StopwatchCertifiedRepository stopwatchCertifiedRepository;
	
	@Autowired
	public TodoListService(TodoListRepository todoListRepository, ModelMapper modelMapper, StudyMemberService studyMemberService, BoardService boardService,
			StudyMemberRepository studyMemberRepository, StopwatchCertifiedRepository stopwatchCertifiedRepository) {
		this.todoListRepository = todoListRepository;
		this.modelMapper = modelMapper;
		this.studyMemberService = studyMemberService;
		this.boardService = boardService;
		this.studyMemberRepository = studyMemberRepository;
		this.stopwatchCertifiedRepository = stopwatchCertifiedRepository;
	}

	public List<TodoListDTO> selectTodoList(Date todoListStartDate, int studyNo, Long memberNo) {
  
		List<StudyMemberDTO> studyList = studyMemberService.selectAllStudy(memberNo);
		
		List<BoardDTO> study = boardService.selectBoard(studyList);
		
		String studyId = study.get(studyNo).getStudyId();	
		
		List<TodoList> todoList = todoListRepository.findAllBytodoListStartDateAndStudyId(todoListStartDate, studyId);		
		
		return todoList.stream().map(todo -> modelMapper.map(todo, TodoListDTO.class)).collect(Collectors.toList());
	}

	public void modifyTodoList(String certifiedExplain, Long stopwatchTime, String todoListId) {

		TodoList todoList = todoListRepository.findBytodoListId(todoListId);
		todoList.getCertified().setCertifiedExplain(certifiedExplain);
		todoList.getStopwatch().setStopwatchTime(stopwatchTime);

		log.info("[TodoController] todoListDate : {}", todoListId);
		
		List<StopwatchCertified> modifyStopwatch = stopwatchCertifiedRepository.findByTodoListId(todoListId);

		log.info("[TodoController] modifyStopwatch : {}", modifyStopwatch);
		
		for ( StopwatchCertified stopwatch : modifyStopwatch ) {
			
			stopwatch.setRemainTime(stopwatchTime);
			
		}
	}

	public void playStopwatch(Long stopwatchTime, String todoListId) {

		TodoList todoList = todoListRepository.findBytodoListId(todoListId);
		todoList.getStopwatch().setStopwatchTime(stopwatchTime);
		
	}

	public void registTodoList(TodoListDTO todoListDTO) {
		
		TodoList todoList = todoListRepository.findBytodoListId(todoListDTO.getTodoListId());
		todoList.setTodoListSort("SC");

	}

	public void deleteTodoList(String todoListId) {

		TodoList todoList = todoListRepository.findBytodoListId(todoListId);
		todoList.setTodoListSort("N");
		
	}

}
