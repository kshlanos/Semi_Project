package com.semi.project.login.controller;


import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.semi.project.login.dto.MemberDTO;
import com.semi.project.login.service.AuthenticationService;
import com.semi.project.login.service.MemberService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {
	
	private final PasswordEncoder passwordEncoder;
    private final MessageSourceAccessor messageSourceAccessor;
    private final MemberService memberService;
    private final AuthenticationService authenticationService;
  
    public LoginController(MessageSourceAccessor messageSourceAccessor, MemberService memberService, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.messageSourceAccessor = messageSourceAccessor;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
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
	
	    
	@GetMapping(value = {"/forgotId"})
	public String forgotId() {
		
		return "/login/forgotId";
	}
	
	@GetMapping(value = {"/forgotPassword"})
	public String forgotPassword() {
		
		return "/login/forgotPassword";
		
	}
	  protected Authentication createNewAuthentication(Authentication currentAuth, String memberId) {
	    	
	    	UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
	    	UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
	    	newAuth.setDetails(currentAuth.getDetails());
	        return newAuth;
	        
	  }
	  
	  @PostMapping("/mypage/modify")
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
	    	
	    	
	    	rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));
	    	
	    	log.info("[MemberController] modifyMember ==============================");
	    	
	    	return "redirect:/";
	    }
	  
	  
}







