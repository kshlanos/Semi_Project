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
import com.semi.project.study.detail.service.StudyMemberService;
import com.semi.project.study.todo.dto.TodoListDTO;
import com.semi.project.study.todo.entity.TodoList;
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
	
	@Autowired
	public TodoListService(TodoListRepository todoListRepository, ModelMapper modelMapper, StudyMemberService studyMemberService, BoardService boardService) {
		this.todoListRepository = todoListRepository;
		this.modelMapper = modelMapper;
		this.studyMemberService = studyMemberService;
		this.boardService = boardService;
		
	}

	public List<TodoListDTO> selectTodoList(Date todoListStartDate, int studyNo, Long memberNo) {

		log.info("[TodoService] todoListStartDate : {}",todoListStartDate);
		log.info("[TodoService] studyNo : {}", studyNo);
		log.info("[TodoService] memberNo : {}", memberNo);
  
		List<StudyMemberDTO> studyList = studyMemberService.selectAllStudy(memberNo);
		
		List<BoardDTO> study = boardService.selectBoard(studyList);
		
		String studyId = study.get(studyNo).getStudyId();	
		
		log.info("[TodoService] studyId : {}", studyId);
		
		List<TodoList> todoList = todoListRepository.findAllBytodoListStartDateAndStudyId(todoListStartDate, studyId);
		
		log.info("[TodoService] todoList : {}", todoList);			
		
		return todoList.stream().map(todo -> modelMapper.map(todo, TodoListDTO.class)).collect(Collectors.toList());
	}

	public void modifyTodoList(String certifiedExplain, Long stopwatchTime, String todoListId) {

		TodoList todoList = todoListRepository.findBytodoListId(todoListId);
		todoList.getCertified().setCertifiedExplain(certifiedExplain);
		todoList.getStopwatch().setStopwatchTime(stopwatchTime);
		
	}

	public void playStopwatch(Long stopwatchTime, String todoListId) {

		TodoList todoList = todoListRepository.findBytodoListId(todoListId);
		todoList.getStopwatch().setStopwatchTime(stopwatchTime);
		
	}

}
