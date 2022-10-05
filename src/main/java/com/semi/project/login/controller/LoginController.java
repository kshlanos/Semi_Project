package com.semi.project.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller //테스트
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/login")
	public void loginForm() {
		
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
	
	
}






