package com.semi.project.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.semi.project.board.dto.AppliyDTO;
import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.entity.Appliy;
import com.semi.project.board.entity.Board;
import com.semi.project.board.repository.AppliyRepository;
import com.semi.project.board.repository.BoardRepository;
import com.semi.project.study.detail.dto.StudyMemberDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
public class BoardService {
	
	public static final int TEXT_PAGE_SIZE = 10; 
	public static final String SORT_BY = "studyId";
	public static final String ACTIVE_STATUS = "N";
	public static final String STUDY_ID = "studyId";

	private final BoardRepository boardRepository;
	private final AppliyRepository appliyRepository;
	private final ModelMapper modelMapper;

	
	/* 게시판 목록 조회 */
	public BoardService(BoardRepository boardRepository, AppliyRepository appliyRepository, ModelMapper modelMapper) {
		this.boardRepository = boardRepository;
		this.appliyRepository = appliyRepository;
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
	public BoardDTO selectDetailMember(Long StudyId) {
		
		Board board = boardRepository.findByStudyIdAndStudyStatus(StudyId, ACTIVE_STATUS);
		board.setBoardCount(board.getBoardCount() + 1);
		
		return modelMapper.map(board, BoardDTO.class);
	}
	
	
	

	/* 게시글 작성 */
	public void registBoard(BoardDTO board) {
		
		board.setStudyId(null);
		boardRepository.save(modelMapper.map(board, Board.class));
		
	}

	public List<BoardDTO> selectBoard(List<StudyMemberDTO> studyList) {
		
//		List<List<Board>> List = new ArrayList<List<Board>>();
//		
//		for ( StudyMemberDTO memberDTO : studyList ) {
//			
//			List<Board> boardList = boardRepository.findByStudyId(memberDTO.getStudyId());
//			List.add(boardList);
//		}
		
		List<Board> boardList = new ArrayList<Board>();
		
		for ( StudyMemberDTO memberDTO : studyList ) {
			
			boardList.add(boardRepository.findByStudyId(memberDTO.getStudyId()));
		}
		
    	log.info("[BoardService] boardList : {}", boardList);
		
		
		return boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());
		
	}

	public List<AppliyDTO> reqbtn(AppliyDTO reqbtn) {
		
		appliyRepository.save(modelMapper.map(reqbtn, Appliy.class));
		
//		List<Appliy> reqList 
//			= appliyRepository.findByRefStudyIdAndAppliyStatus(reqbtn.getRefStudyId(), ACTIVE_STATUS);
		

		return null;
	}

	public BoardDTO selectStudy(Long studyId) {

		Board study = boardRepository.findByStudyId(studyId);
		
		return modelMapper.map(study, BoardDTO.class);
	}

}
