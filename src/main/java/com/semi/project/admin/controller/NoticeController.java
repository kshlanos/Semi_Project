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
	

	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	
	
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
	

	/* 이벤트 목록 조회 구현 */
	@GetMapping(value = {"/noticeEvent"})
	public String selectAllEventList(@RequestParam(defaultValue="1") int page, @RequestParam(required=false) String searchValue, Model model) {
		
		log.info("[이벤트 컨트롤러] ======================= ");
		log.info("[이벤트 컨트롤러] parameter page : {}", page);
		log.info("[BoardController] param searchValue : {}", searchValue);
		
		Page<NoticeDTO> eventList = noticeService.selectEventList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(eventList);
		
		log.info("[이벤트 컨트롤러] EventList : {}", eventList);
		eventList.get().forEach(event -> log.info(event.toString()));
		log.info("[이벤트 컨트롤러] paging : {}", paging);
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("paging", paging);
		
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
		
		return "admin/noticeEvent";
	}

	
	/* 이벤트 상세 조회 구현 */
	@GetMapping(value = {"/noticeEventDetail"})
	public String goNoticeEventDetail(Model model, Long noticeNo) {
		
		log.info("[이벤트게시글 확인] ===========================");
		log.info("[이벤트게시글 확인] 게시글번호 : {}", noticeNo);		
		NoticeDTO notice = noticeService.selectNoticeEventDetail(noticeNo);

		log.info("[이벤트게시글 확인] 썸넬 : {}", notice);	
		model.addAttribute("notice", notice);

		return "/admin/noticeEventDetail";
	}

	
	/* 이벤트 등록 기능 구현 */
	@GetMapping(value = {"/registNoticeEvent"})
	public String goRegistNoticeEvent() {
		
		
		return "/admin/registNoticeEvent";
	}


	@PostMapping(value = {"/registNoticeEvent"})
	public String RegistNoticEventThumbnail(NoticeDTO notice, List<MultipartFile> attachImage,
				@AuthenticationPrincipal MemberDTO member) {

		log.info("[EventThumbnail] ======== ");
		
		log.info("notice Request : {}", notice);
		log.info("attachImage Request : {}", attachImage);
		
		String rootLocation = IMAGE_DIR;
		
		String fileUploadDirectory = rootLocation + "/upload/original";
		String thumbnailDirectory = rootLocation + "/upload/thumbnail";
		
		File directory = new File(fileUploadDirectory);
		File directory2 = new File(thumbnailDirectory);		
		
		log.info("directory : {}", directory);
		log.info("directory2 : {}", directory2);
		
		/* 현재 아무 디렉토리를 가지고 있지 않을 경우, 생성하지 않는 경우를 가정 */
		if (!directory.exists() || !directory2.exists()) {
			log.info("폴더 생성 : {}", directory.mkdirs());
			log.info("폴더 생성 : {}", directory2.mkdirs());
		}
		
		/* 업로드 파일에 대한 정보 */
		List<AppendDTO> noticeAppendFileList = new ArrayList<>();
		try {
			for (int i = 0; i < attachImage.size(); i++) {
				/* 첨부파일이 실제로 있는 경우에만 로직 수행! */
				if(attachImage.get(i).getSize() > 0) {
					String originalFileName = attachImage.get(i).getOriginalFilename();
					
					log.info("originalFileName : " + originalFileName);
					
					/* 이름 자르기 */
					/* UUID = 유니크한 아이디를 만들어 줌 */
					String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
					String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;
					
					log.info("savedFileName : " + savedFileName);
					
					/* 디렉토리에 저장 */
	
					attachImage.get(i).transferTo(new File(fileUploadDirectory + "/" + savedFileName));
					
					/* DB에 저장 과정 */
					AppendDTO fileInfo = new AppendDTO();
					fileInfo.setAppendName(originalFileName);
					fileInfo.setAppendInhName(savedFileName);
					fileInfo.setAppendPath("/upload/original/");
					
					if(i == 0) {
						fileInfo.setAppendType("TITLE");
						/* 대표 사진에 대한 썸네일을 가공하여, 저장해야 함 */
						Thumbnails.of(fileUploadDirectory + "/" + savedFileName).size(380, 220)
								.toFile(thumbnailDirectory + "/thumbnail_" + savedFileName);
						fileInfo.setAppendThumbnailPath("/upload/thumbnail/thumbnail_" + savedFileName);
					} else {
						fileInfo.setAppendType("BODY");
					}
					noticeAppendFileList.add(fileInfo);	
				}
			}
			
			log.info("[EventThumbnail] noticeAppendFileList : ()", noticeAppendFileList);
			
			notice.setNoticeAppendFileList(noticeAppendFileList);
			
			log.info("[EventThumbnail] thumbnail : ()", notice);
			
			notice.setNoticeWriter(member);
			noticeService.registThumbnail(notice);
			
		
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			/* 실패 시 저장된 파일을 삭제하는 로직 */
			for(AppendDTO append : noticeAppendFileList) {
				
				File deleteFile = new File(append.getAppendPath() + "/" + append.getAppendInhName());
				deleteFile.delete();
				
				File deleteThumbnail = new File(thumbnailDirectory + "/thumbnail_" + append.getAppendInhName());
				deleteThumbnail.delete();
			}
			
		}
		
		log.info("[EventThumbnail] ======== ");
		
		return "redirect:/admin/noticeEvent";
	}


	/* 이벤트 삭제 기능 구현 */
	@PostMapping(value = {"/deleteNotice"})
	public String deleteEvent(@ModelAttribute NoticeDTO deleteNotice, Long noticeNo) {
		
		log.info("[noticeController] noticeNo : {}", noticeNo);
		noticeService.removeNotice(deleteNotice);
		
		log.info("[UserController] deleteNotice : {}", deleteNotice);
		
		return "redirect:/admin/noticeEvent";
	}
	

	
	
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
	
	
	/* 회원수정 */
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
	
	
	/* 회원 문의 조회 기능 구현 */
	@GetMapping(value = {"/userQnaListAdmin"})
	public String getUserQnaAdmin(@RequestParam(defaultValue="1") int page, Long inquiryRefNo, Model model) {

		Page<InquiryDTO> qnaMain = userService.selectQnaList(page, inquiryRefNo);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(qnaMain);
		
		model.addAttribute("qnaMain", qnaMain);
		model.addAttribute("paging", paging);
		
		log.info("[QNAcontroller 테스트] ================================");
		log.info("[QNAcontroller 테스트] qnaMain : {}", qnaMain);
		log.info("[QNAcontroller 테스트] paging : {}", paging);		
		log.info("[QNAcontroller 테스트] ================================");		
		
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
		
		log.info("[코멘트 테스트] ================================");
		log.info(" registComment : {}", registComment);
		log.info("[코멘트 테스트] ================================");
		
		registComment.setCommentWriter(member);
		
		List<CommentDTO> commentList = userService.registComment(registComment);
		log.info(" commentList : {}", commentList);
		log.info("[코멘트 테스트] ================================");
		
		return ResponseEntity.ok(commentList);
	}
	
	
	/* 회원 문의 답변 불러오기 구현 */
//	@GetMapping("/loadComment")
//	public ResponseEntity<List<CommentDTO>> loadComment(CommentDTO loadComment) {
//		
//		log.info("[불러오기 코멘트 테스트] ================================");
//		log.info(" refInquiry : {}", loadComment);
//		
//		List<CommentDTO> commentList = userService.loadComment(loadComment);
//
//		log.info(" commentList : {}", commentList);
//		log.info("[불러오기 코멘트 테스트] ================================");
//		
//		return ResponseEntity.ok(commentList);
//	}
	
	
	@GetMapping("/loadComment")
	public ResponseEntity<List<CommentDTO>> loadComment(Inquiry refInquiry) {
		
		log.info("[불러오기 코멘트 테스트] ================================");
		log.info(" refInquiry : {}", refInquiry);
		
		List<CommentDTO> commentList = userService.loadComment(refInquiry);

		log.info(" commentList : {}", commentList);
		log.info("[불러오기 코멘트 테스트] ================================");
		
		return ResponseEntity.ok(commentList);
	}
	
	
	@PostMapping("/removeComment")
	public ResponseEntity<String> removeComment(@RequestBody CommentDTO removeComment) {
		log.info("[코멘트 삭제 테스트] ================================");
		log.info(" loadComment : {}", removeComment);
		
		userService.removeComment(removeComment);
		
		return ResponseEntity.ok("답변이 삭제되었습니다.");
	}

	

	
}
