package com.semi.project.login.controller;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.service.AuthenticationService;
import com.semi.project.login.service.FinePwdMailService;
import com.semi.project.login.service.MemberService;
import com.semi.project.login.service.RegistMailService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
	
	private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;
    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final RegistMailService registMailService;
    private final FinePwdMailService finePwdMailService;
  
    public LoginController(MessageSourceAccessor messageSourceAccessor, MemberService memberService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, RegistMailService registMailService, FinePwdMailService finePwdMailService) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
		this.registMailService = registMailService;
		this.finePwdMailService = finePwdMailService;
    }


	
	@GetMapping("/login")
	public String loginForm() {
		
		return "login/login";
	}
	
	@PostMapping("/loginfail")
	    public String loginFailed(RedirectAttributes rttr) {
	    	
	    	rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
	    	
	    	return "redirect:/login/login";
	}
	
	
	@GetMapping(value = {"/register"})
	public String register() {
		
		return "/login/register";
	}
	
	/* 회원 가입 */
    @PostMapping("/register")
    public String registMember(@ModelAttribute MemberDTO member,
    		@RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
    		RedirectAttributes rttr, @RequestParam String year, @RequestParam String month, @RequestParam String day ) {
    	
    	log.info("[MemberController] registMember ==============================");
    	
    	String address = zipCode + "$" + address1 + "$" + address2;
    	member.setMemberAddress(address);
    	member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
    	member.setMemberPhone(member.getMemberPhone().replace("-", ""));
    	
    	String birth = year + "-"+ month + "-" + day;
    	log.info("[MemberController] registMember request birth : " + birth);
    	java.sql.Date date = java.sql.Date.valueOf(birth);
    	member.setMemberBirth(date);
    	log.info("[MemberController] registMember request date : " + date);
    	
    	member.setMemberPhone(member.getMemberPhone());
    	
    	log.info("[MemberController] registMember request Member : " + member);
    	
    	memberService.registMember(member);
    	
    	rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("login.regist"));
    	
    	log.info("[MemberController] registMember ==============================");
    	
    	return "redirect:/";
    }
	
	/* 아이디 중복 체크 - 비동기 통신 */
    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO member) {
    	
    	log.info("[MemberController] checkDuplication ========================== ");
    	
    	String result = "사용 가능한 아이디입니다.";
    	log.info("[MemberController] Request Check ID : {}", member.getMemberId());
    	
    	if(memberService.selectMemberById(member.getMemberId())) {
    		log.info("[MemberController] Already Exist");
    		result = "중복 된 아이디가 존재합니다.";
    	}
    	
    	log.info("[MemberController] checkDuplication ========================== ");
    	
    	return ResponseEntity.ok(result);
    }
	
    // 이메일 인증
 	@PostMapping("/mailConfirm")
 	@ResponseBody
 	String mailConfirm(@RequestParam("email") String email) throws Exception {

 		String code = registMailService.sendSimpleMessage(email);
 		System.out.println("인증코드 : " + code);
 		return code;
 	}
	    
	@GetMapping(value = {"/forgotId"})
	public String forgotId() {
		
		return "/login/forgotId";
	}
	
	@ResponseBody
	@PostMapping("/forgotId")
	public String findIdByMemberNameAndMemberPhone(@RequestParam("memberName") String memberName, @RequestParam("memberPhone") String memberPhone) {
		
		String findId = memberService.findIdByMemberNameAndMemberPhone(memberName, memberPhone);
		
		log.info("findId : " + findId);
		
		return findId;
	}
	
	@GetMapping(value = {"/forgotPassword"})
	public String forgotPassword() {
		
		return "/login/forgotPassword";
		
	}
	
	// 일반 회원 비밀번호 찾기 및 임시 패스워드로 변경
		@PostMapping("/forgotPassword")
		@ResponseBody
		String findIdByMemberIdAndMemberNameAndMemberEmail(@ModelAttribute MemberDTO updatepassword,@RequestParam("memberId") String memberId, @RequestParam("memberName") String memberName,
				@RequestParam("memberEmail") String memberEmail)  throws Exception {
			 System.out.println(memberId + " : " + memberName + " : " + memberEmail);
			 
			
			String mdto = memberService.findIdByMemberIdAndMemberNameAndMemberEmail(memberId, memberName, memberEmail);
			log.info(memberId);
			log.info(memberName);
			log.info(memberEmail);
			if(mdto != null) {
				// 임시 패스워드 메일 발송 및 변수 저장
				String tempPw = passwordEncoder.encode(finePwdMailService.sendSimpleMessage(memberEmail));
				log.info(tempPw);
				// System.out.println("tempPw : " + tempPw);
				// 임시 패스워드 db 에 저장
				memberService.changeTempPw(tempPw, memberId);
			return tempPw;
			}
			return null;
		}
	 
	  protected Authentication createNewAuthentication(Authentication currentAuth, String memberId) {
	    	
	    	UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
	    	UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
	    	newAuth.setDetails(currentAuth.getDetails());
	        return newAuth;
	        
	  }
	  
	 
	  
	  
}







