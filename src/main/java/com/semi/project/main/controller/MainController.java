package com.semi.project.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

	@GetMapping(value = {"/", "/main"})
	public String main() {
		
		return "/main/main";
	}
	
	@PostMapping(value="/")
	public String redirectMain() {
		
		return "redirect:/";
	}
	
	@GetMapping(value = {"/login"})
	public String login() {
		
		return "/login/login";
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
		//e
	}
	
	
}







