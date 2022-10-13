package com.semi.project.study.detail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.study.detail.entity.Study;

public interface StudyRepository extends JpaRepository<Study, Integer>{

	List<Study> findByMemberNo(Long memberNo);

}
