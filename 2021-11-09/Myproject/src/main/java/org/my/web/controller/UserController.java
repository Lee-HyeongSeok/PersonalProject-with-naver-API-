package org.my.web.controller;

import javax.servlet.http.HttpSession;

import org.my.web.naverAPI.NaverLoginBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/login")
public class UserController {
	private NaverLoginBO naverLoginBO;
	private String apiResult=null;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	/* 네이버 아이디로 로그인 참고 
	 * https://bumcrush.tistory.com/151
	 */
	@RequestMapping(value="/loginPopWindow", method=RequestMethod.GET)
	public String loginPopWindowGetMethod(Model model, HttpSession session) throws Exception {
		// 네아로 인증 URL을 생성, NaverLoginBO 클래스의 getAuthorizationUrl 메서드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("url", naverAuthUrl);
		
		return "login/loginPopWindow";
	}
	
	// 일반 로그인 처리 프로세스 
	@RequestMapping(value="/loginProcess", method=RequestMethod.POST)
	public String loginProcessMethod(@RequestParam String email, @RequestParam String pwd) {
		System.out.println(email+", "+pwd);
		
		// 로그인 처리 이후 session 설정 및 model 객체에 저장 
		// home.jsp에서 로그인 처리 해야 함 
		
		return "redirect:/home";
	}
	
	// 네이버 아이디로 로그인 처리 프로세스
	@RequestMapping(value="/naverLoginCallBack", method=RequestMethod.POST)
	public String naverLoginCallBackMethod() {
		return "";
	}
}
