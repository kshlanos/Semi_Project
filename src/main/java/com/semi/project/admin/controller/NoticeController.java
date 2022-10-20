package com.semi.project.admin.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semi.project.admin.dto.NoticeDTO;
import com.semi.project.admin.entity.NoticeType;
import com.semi.project.admin.service.NoticeService;
import com.semi.project.admin.service.UserService;
import com.semi.project.board.dto.AppendDTO;
import com.semi.project.board.dto.CommentDTO;
import com.semi.project.board.entity.Comment;
//import com.semi.project.admin.service.UserService;
import com.semi.project.common.Pagenation;
import com.semi.project.common.PagingButtonInfo;
import com.semi.project.login.dto.CustomUser;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.entity.Inquiry;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/admin")
public class NoticeController {
	
	/* commit test */
	private final NoticeService noticeService;
	private final UserService userService;
	private final MessageSourceAccessor messageSourceAccesor;
	
	public NoticeController(NoticeService noticeService, UserService userService, MessageSourceAccessor messageSourceAccesor) {
		this.noticeService = noticeService;
		this.userService = userService;
		this.messageSourceAccesor = messageSourceAccesor;
	}
	

	
	
	@GetMapping("/login/login")
	public String loginForm() {
		
		return "redirect:/login/login";
	}
	
	@GetMapping("/login/register")
	public String registerForm() {
		
		return "redirect:/login/register";
	}
	
	
	/* 공지사항 목록 조회 구현 */
	@GetMapping(value = {"/noticeMain"})
	public String getNoticeMain(@RequestParam(defaultValue="1") int page, @RequestParam(required=false) String searchValue, Model model) {
	
		
		Page<NoticeDTO> noticeMain = noticeService.selectNoticeList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(noticeMain);
		
		
		model.addAttribute("noticeMain", noticeMain);
		model.addAttribute("paging", paging);
		
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
		
		return "admin/noticeMain";
	}
	
	
	/* 공지사항 상세 조회 구현 */
	@GetMapping(value = {"/noticeMainDetail"})
	public String getNoticeMainDetail(Model model, Long noticeNo) {
		
		NoticeDTO notice = noticeService.selectNoticeDetail(noticeNo);
		
		model.addAttribute("notice", notice);
		return "/admin/noticeMainDetail";
		
	}


	/* 공지사항 작성 기능 구현 */
	@GetMapping(value = {"/registNoticeMain"})
	public String goRegistNoticeMain() {
		
		return "/admin/registNoticeMain";
	}
	
	@PostMapping(value = {"/registNoticeMain"})
	public String registNoticeMain(NoticeDTO notice, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {		
		log.info("[NoticController] ====== 작성값 체크 ====== ");
		log.info(" registNotice request : {}", notice);
		
		notice.setNoticeWriter(member);

		log.info(" member : {}", member);
		noticeService.registNoticeMain(notice);
		
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("admin.registNoticeMian"));
		
		log.info("[NoticController] ====== ======== ====== ");
		
		return "redirect:/admin/noticeMain";
	}
	

	/* 공지사항 수정 기능 구현 */
	@GetMapping(value = {"/updateNoticeMain"})
	public String goModifyNoticeMain(Model model, Long noticeNo) {
		
		NoticeDTO notice = noticeService.goUpdateNoticeMain(noticeNo);
		model.addAttribute("notice", notice);
		return "/admin/updateNoticeMain";
	}
	
	
	@PostMapping(value = {"/updateNoticeMain"})
	public String modifyNoticeMain(@ModelAttribute NoticeDTO updateNotice) {
		
		log.info("updateNotice request : {}", updateNotice);
		noticeService.modifyNoticeMain(updateNotice);
		return "redirect:/admin/noticeMain";
		
	}
	
	
	/* 공지사항 삭제 기능 구현 */
	@PostMapping(value = {"/deleteNoticeMain"})
	public String deleteNotice(@ModelAttribute NoticeDTO deleteNotice, Long noticeNo) {
		
		log.info("[noticeController] noticeNo : {}", noticeNo);
		noticeService.removeNotice(deleteNotice);
		
		log.info("[UserController] deleteNotice : {}", deleteNotice);
		
		return "redirect:/admin/noticeMain";
	}
	

	/* -------------------------------------------------------------------------------- */
	



	
	
	/* -------------------------------------------------------------------------------- */	
	
	
	/* 회원 관리 기능 구현 */
	@GetMapping(value = {"/userListAdmin"})
	public String getUserListAdmin(@RequestParam(defaultValue="1") int page, @RequestParam(required=false) String searchValue, Model model) {
		Page<MemberDTO> userList = userService.selectUserList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(userList);
		
		log.info("[UserController] ==============");
		log.info("[UserController] param page : {}", page);
		log.info("[UserController] param searchValue : {}", searchValue);
		log.info("[UserController] ==============");
		
		
		model.addAttribute("userList", userList);
		model.addAttribute("paging", paging);
		
		log.info("[UserController] userList : {}", userList);
		log.info("[UserController] userList : {}", userList.getContent());



		
		return "/admin/userListAdmin";
	}
	
	
	/* 회원상세 및 수정 조회 */
	@GetMapping(value = {"/userUpdateAdmin"})
	public String getUserUpdateAdmin(Model model, Long memberNo) {
		
		MemberDTO user = userService.updateUserAdmin(memberNo);
		model.addAttribute("user", user);
		return "/admin/userUpdateAdmin";
	}
	
	
	/* 회원 수정 */
	@PostMapping(value = "/userUpdateAdmin")
	public String modifyUserAdmin(@ModelAttribute MemberDTO updateUser) {
		
		
		log.info("[UserController] ModifyUser request user : {}", updateUser);
		userService.modifyUser(updateUser);
		
		return "redirect:/admin/userListAdmin";
	}
	
	
	/* 회원 비활성 */
	@PostMapping(value = {"/userDeleteAdmin"})
	public String deleteUserAdmin(@ModelAttribute MemberDTO deleteUser, Long memberNo) {
		
		log.info("[UserController] memberNo : {}", memberNo);
		userService.removeUser(deleteUser);
		
		log.info("[UserController] deleteUser : {}", deleteUser);
		
		return "redirect:/admin/userListAdmin";
	}
	
	
	/* -------------------------------------------------------------------------------- */	
	
	
	/* 회원 문의 조회 기능 구현 */
	@GetMapping(value = {"/userQnaListAdmin"})
	public String getUserQnaAdmin(@RequestParam(defaultValue="1") int page, Long inquiryRefNo, Model model) {

		Page<InquiryDTO> qnaMain = userService.selectQnaList(page, inquiryRefNo);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(qnaMain);
		
		
		model.addAttribute("qnaMain", qnaMain);
		model.addAttribute("paging", paging);		
		
		return "/admin/userQnaListAdmin";
	}
	
	
	
	/* 회원 문의 상세 조회 기능 구현 */
	@GetMapping(value = {"/userQnaDetail"})
	public String getUserQnaDetail(Model model, Long inquiryNo) {
		
		InquiryDTO inquiry = userService.selectQnaDetail(inquiryNo);
		
		model.addAttribute("inquiry", inquiry);
		
		return "/admin/userQnaDetail";
	}
	
	
	/* 회원 문의 답변 기능 구현 */
	@PostMapping("/registComment")
	public ResponseEntity<List<CommentDTO>> registInquiryComment(@RequestBody CommentDTO registComment,
			@AuthenticationPrincipal MemberDTO member) {
		
		registComment.setCommentWriter(member);
		
		List<CommentDTO> commentList = userService.registComment(registComment);
		
		return ResponseEntity.ok(commentList);
	}
	
	
	/* 회원 문의 답변 불러오기 구현 */
	
	@GetMapping("/loadComment")
	public ResponseEntity<List<CommentDTO>> loadComment(Inquiry refInquiry) {
		
		List<CommentDTO> commentList = userService.loadComment(refInquiry);
		
		return ResponseEntity.ok(commentList);
	}
	
	
	@PostMapping("/removeComment")
	public ResponseEntity<String> removeComment(@RequestBody CommentDTO removeComment) {
		
		userService.removeComment(removeComment);
		
		return ResponseEntity.ok("답변이 삭제되었습니다.");
	}

	

	
}
