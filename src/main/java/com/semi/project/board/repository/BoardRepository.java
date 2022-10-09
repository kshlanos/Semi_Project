package com.semi.project.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, String>{

	Page<Board> findByStudyStatus(char activeStatus, Pageable pageable);

	Board findByStudyBoardNoAndStudyStatus(String studyBoardNo, char studyStatus);



}
