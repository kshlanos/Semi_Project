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
	public static final String SORT_BY = "studyBoardNo";
	public static final char ACTIVE_STATUS = 'N';
	public static final String STUDY_ID = "studyId";

	private final BoardRepository boardRepository;
	private final ModelMapper modelMapper;

	
	/* 게시판 목록 조회 */
	public BoardService(BoardRepository boardRepository, ModelMapper modelMapper) {
		this.boardRepository = boardRepository;
		this.modelMapper = modelMapper;
	}

	public Page<BoardDTO> selectStudyList(int page, String searchValue) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Board> studyList = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
			studyList = boardRepository.findBySearchValue(ACTIVE_STATUS, searchValue, pageable);
		} else {
			studyList = boardRepository.findByStudyStatus(ACTIVE_STATUS, pageable);
		}
		
		return studyList.map(board -> modelMapper.map(board, BoardDTO.class));
	}

	

	
	
	
	
	
	/* 게시글 상세 조회 */
	public BoardDTO selectDetailMember(Long BoardNo) {
		
		Board board = boardRepository.findByStudyBoardNoAndStudyStatus(BoardNo, ACTIVE_STATUS);
		board.setBoardCount(board.getBoardCount() + 1);
		
		return modelMapper.map(board, BoardDTO.class);
	}
	
	
	

	/* 게시글 작성 */
	public void registBoard(BoardDTO board) {
		
		board.setStudyId(STUDY_ID);
		boardRepository.save(modelMapper.map(board, Board.class));
		
	}

}
