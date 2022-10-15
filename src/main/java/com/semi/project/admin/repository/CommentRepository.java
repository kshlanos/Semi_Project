package com.semi.project.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semi.project.board.entity.Comment;
import com.semi.project.mypage.entity.Inquiry;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByRefInquiryAndCommentStatus(Inquiry refInquiry, String commentStatus);

	Comment findByCommentNo(Long commentNo);




	
	
}
