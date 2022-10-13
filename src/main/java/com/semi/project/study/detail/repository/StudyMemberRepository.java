package com.semi.project.study.detail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.study.detail.entity.StudyMember;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Integer> {

	List<StudyMember> findByMemberNo(Long memberNo);

}
