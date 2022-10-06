package com.semi.project.login.controller;


import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
//	 @PostMapping("/regist")
//	    public String registMember(@ModelAttribute MemberDTO member,
//	    		@RequestParam String zipCode, @RequestParam String address1, @RequestParam String address2,
//	    		RedirectAttributes rttr) {
//	    	
//	    	
//	    	
//	    	return "redirect:/";
//	    }
	
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
}







