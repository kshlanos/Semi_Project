package com.semi.project.board.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semi.project.board.entity.Board;


public interface BoardRepository extends JpaRepository<Board, String>{

	Page<Board> findByStudyStatus(char studyStatus, Pageable pageable);

	@Query("SELECT b " +
			 "FROM Board b " +
			"WHERE b.studyStatus = :studyStatus " +
			  "AND (b.studyTitle LIKE '%' || :searchValue || '%' " +
			   "OR b.studyContent LIKE '%' || :searchValue || '%' " +
			   "OR b.studyTag LIKE '%' || :searchValue || '%' " +
			   "OR b.studyPlace LIKE '%' || :searchValue || '%' )")
	Page<Board> findBySearchValue(@Param("studyStatus") char studyStatus, @Param("searchValue") String searchValue, Pageable pageable);

	
	Board findByStudyBoardNoAndStudyStatus(Long boardNo, char studyStatus);



}
