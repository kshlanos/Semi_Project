package com.semi.project.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import com.semi.project.admin.repository.CommentRepository;
import com.semi.project.admin.repository.QnaRepository;
import com.semi.project.admin.repository.UserRepository;
import com.semi.project.board.dto.CommentDTO;
import com.semi.project.board.entity.Comment;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.entity.Member;
import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.entity.Inquiry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "memberNo"; /* 회원목록 조회 정렬 기준 */
	public static final String SORT_BY_QNA = "inquiryNo"; /* 문의조회 정렬 기준 */
	public static final String ACTIVE_STATUS = "N";
	public static final Long REF_NO = null;
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final QnaRepository qnaRepository;
	private final CommentRepository commentRepository;
	
	public UserService(UserRepository userRepository, ModelMapper modelMapper, QnaRepository qnaRepository, CommentRepository commentRepository) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.qnaRepository = qnaRepository;
		this.commentRepository = commentRepository;
	}
	
	
	public Page<MemberDTO> selectUserList(int page, String searchValue) {
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).ascending());
		Page<Member> userList = null;
		
		if(searchValue != null && !searchValue.isEmpty()) {
		} else {
			userList = userRepository.findByMemberStatus(ACTIVE_STATUS, pageable);
		}
		
		return userList.map(member -> modelMapper.map(member, MemberDTO.class));
		
	}


	public MemberDTO updateUserAdmin(Long memberNo) {
		
		Member user = userRepository.findByMemberNo(memberNo);
		
		return modelMapper.map(user, MemberDTO.class);
	}


	public void modifyUser(MemberDTO updateUser) {
		
		Member savedUser = userRepository.findByMemberNo(updateUser.getMemberNo());
		savedUser.setMemberName(updateUser.getMemberName());
		savedUser.setMemberbirth(updateUser.getMemberBirth());
		savedUser.setMemberNickname(updateUser.getMemberNickname());
		savedUser.setMemberAddress(updateUser.getMemberAddress());
		savedUser.setMemberEmail(updateUser.getMemberEmail());
		savedUser.setMemberPhone(updateUser.getMemberPhone());

		
	}
	
	
	public void removeUser(MemberDTO deleteUser) {
	
		Member savedUser = userRepository.findByMemberNo(deleteUser.getMemberNo());
		savedUser.setMemberStatus("Y");
	
	}

	


	public Page<InquiryDTO> selectQnaList(int page, Long inquiryRefNo) {
		
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY_QNA).descending());
		
		Page<Inquiry> qnaMain = qnaRepository.findByInquiryRefNoAndInquiryDelete(REF_NO, ACTIVE_STATUS, pageable);
			return qnaMain.map(inquiry -> modelMapper.map(inquiry, InquiryDTO.class));
	}


	public InquiryDTO selectQnaDetail(Long inquiryNo) {
		
		Inquiry inquiry = qnaRepository.findByInquiryNo(inquiryNo);
		
		return modelMapper.map(inquiry, InquiryDTO.class);
	}


	
	
//	public List<CommentDTO> registComment(CommentDTO registComment) {
//		
//		commentRepository.save(modelMapper.map(registComment, Comment.class));
//		
//		List<Comment> commentList = commentRepository.findByRefInquiryAndCommentStatus(modelMapper.map(registComment.getRefInquiry(), Inquiry.class), ACTIVE_STATUS);
//		
//		return commentList.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
//	}

	public List<CommentDTO> registComment(CommentDTO registComment) {;
		
		commentRepository.save(modelMapper.map(registComment, Comment.class));
		
		List<Comment> commentList = commentRepository.findByRefInquiryAndCommentStatus(modelMapper.map(registComment.getRefInquiry(), Inquiry.class), ACTIVE_STATUS);
		return commentList.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
	}
	
//	public List<CommentDTO> loadComment(CommentDTO loadComment) {
//		
//		List<Comment> commentList = commentRepository.findByRefInquiryAndCommentStatus(modelMapper.map(loadComment.getRefInquiry(), Inquiry.class), ACTIVE_STATUS);
//		return commentList.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
//	}
	



	public List<CommentDTO> loadComment(Inquiry refInquiry) {
		
		List<Comment> commentList = commentRepository.findByRefInquiryAndCommentStatus(refInquiry, ACTIVE_STATUS);
		
		return commentList.stream().map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
	}


	public void removeComment(CommentDTO removeComment) {
		
		Comment foundComment = commentRepository.findByCommentNo(removeComment.getCommentNo());
		foundComment.setCommentStatus("Y");
		
	}




	
	




}
