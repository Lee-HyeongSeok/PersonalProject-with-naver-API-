package org.my.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/login")
public class UserController {
	@RequestMapping(value="/loginPopWindow", method=RequestMethod.GET)
	public String loginPopWindowGetMethod() {
		return "login/loginPopWindow";
	}
	
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String loginProcessMethod(@RequestParam String email, @RequestParam String pwd) {
		System.out.println(email+", "+pwd);
		
		// 로그인 처리 이후 session 설정 및 model 객체에 저장 
		// home.jsp에서 로그인 처리 해야 함 
		
		return "redirect:/home";
	}
}
