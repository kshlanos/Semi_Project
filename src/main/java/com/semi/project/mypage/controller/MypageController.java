package com.semi.project.mypage.controller;

import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semi.project.board.dto.BoardDTO;
import com.semi.project.board.service.BoardService;
import com.semi.project.login.dto.CustomUser;
import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.service.AuthenticationService;
import com.semi.project.login.service.MemberService;
import com.semi.project.mypage.dto.InquiryDTO;
import com.semi.project.mypage.service.InquiryService;
import com.semi.project.study.detail.dto.StudyMemberDTO;
import com.semi.project.study.detail.service.StudyMemberService;

import lombok.extern.slf4j.Slf4j;

/* 테스트 */
@Controller
@Slf4j
@RequestMapping("/mypage")
public class MypageController {
	
	private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;
    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final InquiryService inquiryService;
    private final StudyMemberService studyMemberService;
    private final BoardService boardService;

  
    public MypageController(MessageSourceAccessor messageSourceAccessor, MemberService memberService, PasswordEncoder passwordEncoder,
    							AuthenticationService authenticationService, InquiryService inquiryService, StudyMemberService studyMemberService, BoardService boardService) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.inquiryService = inquiryService;
        this.studyMemberService = studyMemberService;
        this.boardService = boardService;
    }
	
	@GetMapping("/infomodify")
	public String getinfoModify() {
		
		return "mypage/infomodify";
	}
	
	 protected Authentication createNewAuthentication(Authentication currentAuth, String memberId) {
	    	
	    	UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
	    	UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
	    	newAuth.setDetails(currentAuth.getDetails());
	        return newAuth;
	        
	  }
	  /* 회원정보 수정*/
	  @PostMapping("/infomodify")
	    public String modifyMember(@ModelAttribute MemberDTO updateMember,
	    		@RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
	    		@AuthenticationPrincipal MemberDTO loginMember,
	    		RedirectAttributes rttr) {
	    	
	    	log.info("[MemberController] modifyMember ==============================");
	    	
	    	String address = zipCode + "$" + address1 + "$" + address2;
	    	updateMember.setMemberAddress(address);
	    	updateMember.setMemberNo(loginMember.getMemberNo());
	    	
	    	log.info("[MemberController] modifyMember request Member : {}", updateMember);
	    	
	    	memberService.modifyMember(updateMember);
	    	
	    	/* 세션에 저장 되어 있는 로그인 회원의 정보를 변경한다. */
	    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication, loginMember.getMemberId()));
	    	
	    	
	    	rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("mypage.modify"));
	    	
	    	log.info("[MemberController] modifyMember ==============================");
	    	
	    	return "redirect:/mypage/infomodify";
	    }
	
	@GetMapping("/passwordchange")
	public String getpasswordChange() {
		
		return "mypage/passwordchange";
	}
	
	@GetMapping("/passwordcheck")
	public String getpasswordCheck() {
		
		return "mypage/passwordcheck";
	}
	
	@GetMapping("/mystudy")
	public String getmyStudy(Model model, @AuthenticationPrincipal MemberDTO user) {

		List<StudyMemberDTO> studyList = studyMemberService.selectAllStudy(user.getMemberNo());
				
		List<BoardDTO> study = boardService.selectBoard(studyList);
		
		model.addAttribute("studyList", study);
		
    	log.info("[MemberController] studyList : {}", study);
		
		return "/mypage/mystudy";
	}
	
	@GetMapping("/myboard")
	public String getmyboard() {
		
		return "mypage/myboard";
	}
	
	@GetMapping("/inquirywrite")
	public String getwriteInquiry() {
		
		return "mypage/inquirywrite";
	}
	
	@PostMapping("/inquirywrite")
	public String inquiryBoard(InquiryDTO inquiry, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("[MypageController] ==========");
		log.info("[MypageController] inquiryBoard request : {}", inquiry);
		log.info("[MypageController] inquiryBoard request : {}", member);
		
		inquiry.setMember(member);
		inquiryService.inquiryBoard(inquiry);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("mypage.inquirywrite"));
		
		return "redirect:/mypage/inquirycheck";
	}
	
	@GetMapping("/inquirymodify")
	public String getinquiryModify() {
		
		return "mypage/inquirymodify";
	}
	
	@GetMapping("/inquirycheck")
	public String getinquiryCheck() {
		
		return "mypage/inquirycheck";
	}
	
	@GetMapping("/noticecheck")
	public String getnoticeCheck() {
		
		return "mypage/noticecheck";
	}
	
	@GetMapping("/inquiryconfirm")
	public String getinquiryConfirm() {
		
		return "mypage/inquiryconfirm";
	}
	
	@GetMapping("/inforemove")
	public String infoRemove() {
		
		return "mypage/inforemove";
	}
	
}
