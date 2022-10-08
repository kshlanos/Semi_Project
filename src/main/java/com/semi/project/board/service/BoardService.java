package com.semi.project.board.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.entity.Board;
import com.semi.project.board.repository.BoardRepository;

@Service
@Transactional
public class BoardService {
	
	public static final int TEXT_PAGE_SIZE = 10; 
	public static final String SORT_BY = "StudyBoardNo";
	public static final char ACTIVE_STATUS = 'Y';

	private final BoardRepository boardRepository;
	private final ModelMapper modelMapper;
	
	public BoardService(BoardRepository boardRepository, ModelMapper modelMapper) {
		this.boardRepository = boardRepository;
		this.modelMapper = modelMapper;
	}

	public Page<BoardDTO> selectStudyList(int page, String searchValue) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Board> studyList = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			
		} else {
			studyList = boardRepository.findByStudyStatus(ACTIVE_STATUS, pageable);
		}
		
		return studyList.map(board -> modelMapper.map(board, BoardDTO.class));
	}

}
