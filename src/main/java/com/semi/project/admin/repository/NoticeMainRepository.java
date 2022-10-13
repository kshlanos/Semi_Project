package com.semi.project.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.admin.entity.Notice;
import com.semi.project.admin.entity.NoticeType;

public interface NoticeMainRepository extends JpaRepository<Notice, Long> {

	@EntityGraph(attributePaths = {"noticeAppendFileList"})
	Page<Notice> findByNoticeCodeAndNoticeDelete(NoticeType noticeCode, String noticeDelete, Pageable pageable);

	@EntityGraph(attributePaths = {"noticeAppendFileList"})
	Notice findByNoticeNoAndNoticeCodeAndNoticeDelete(Long noticeNo, NoticeType noticeCode, String noticeDelete);

	Notice findByNoticeNo(Long noticeNo);

}
