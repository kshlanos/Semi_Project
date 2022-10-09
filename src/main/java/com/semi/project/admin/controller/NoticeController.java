package com.semi.project.admin.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.semi.project.admin.dto.NoticeDTO;
import com.semi.project.admin.service.NoticeService;
//import com.semi.project.admin.service.UserService;
import com.semi.project.common.Pagenation;
import com.semi.project.common.PagingButtonInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class NoticeController {
	
	private final NoticeService noticeService;
//	private final UserService userService;
	
	public NoticeController(NoticeService noticeService/*, UserService userService*/) {
		this.noticeService = noticeService;
//		this.userService = userService;
	}
	
	@GetMapping(value = {"/noticeMain"})
	public String getNoticeMain(@RequestParam(defaultValue="1") int page, @RequestParam(required=false) String searchValue, Model model) {
	
		
		Page<NoticeDTO> noticeMain = noticeService.selectNoticeList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(noticeMain);
		
		
		model.addAttribute("noticeMain", noticeMain);
		model.addAttribute("paging", paging);
		
		return "admin/noticeMain";
	}
	
	/* 공지사항 게시글 상세 조회 controller */
	@GetMapping(value = {"/noticeMainDetail"})
	public String getNoticeMainDetail(Model model, Long noticeNo) {
		
		NoticeDTO notice = noticeService.selectNoticeDetail(noticeNo);
		
		model.addAttribute("notice", notice);
		return "/admin/noticeMainDetail";
		
	}

	@GetMapping(value = {"/registNoticeMain"})
	public String registNoticeMain() {
		
		return "/admin/registNoticeMain";
	}
	
	/* 공지사항 수정 기능 구현 */
	@GetMapping(value = {"/updateNoticeMain"})
	public String goModifyNoticeMain() {
		
		return "/admin/updateNoticeMain";
	}
	
	@PostMapping(value = {"/updateNoticeMain"})
	public String modifyNoticeMain(@ModelAttribute NoticeDTO updateNotice) {
		
		return "redirect:/";
		
	}
	
	@PostMapping("/removeNoticeMain")
	public ResponseEntity<String> removeNoticeMain(@RequestBody NoticeDTO removeNoticeMain) {
		
		noticeService.removeNoticeMain(removeNoticeMain);
		
		return ResponseEntity.ok("게시글을 삭제하였습니다.");
	}
	
	/* ---------------- */
	
	
	
	
	@GetMapping(value = {"/noticeEvent"})
	public String getNoticeEvent() {
		return "/admin/noticeEvent";
	}
	
	
	
	
	
	/* 회원 관리 기능 구현 */
	
	@GetMapping(value = {"/userListAdmin"})
	public String getUserListAdmin(/*@RequestParam(defaultValue="1") int page, @RequestParam(required=false) String searchValue, Model model*/) {
//		Page<MemberDTO> userList = userService.selectUserList(page, searchValue);
//		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(userList);
//		
//		model.addAttribute("userList", userList);
//		model.addAttribute("paging", paging);
//		
//		if(searchValue != null && !searchValue.isEmpty()) {
//			model.addAttribute("searchValue", searchValue);
//		}

		return "/admin/userListAdmin";
	}

	@GetMapping(value = {"/userQnaListAdmin"})
	public String getUserQnaAdmin() {
		return "/admin/userQnaListAdmin";
	}
	
	
	
}
