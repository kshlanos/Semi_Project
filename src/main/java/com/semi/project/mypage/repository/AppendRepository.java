package com.semi.project.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.board.entity.Append;

/* 이미지등록을위한 repository*/
public interface AppendRepository extends JpaRepository<Append, Long> {

}
