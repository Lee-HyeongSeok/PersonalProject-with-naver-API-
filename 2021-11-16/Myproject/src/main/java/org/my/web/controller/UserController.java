package org.my.web.controller;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.my.web.domain.UserLoginVO;
import org.my.web.naverAPI.NaverLoginBO;
import org.my.web.scriptUtils.ScriptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller
@RequestMapping(value="/login")
public class UserController {
	private NaverLoginBO naverLoginBO;
	private String apiResult=null;
	
	@Autowired
	public void setNaverLoginBO(NaverLoginBO naverLoginBO) {
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
		// 로그인 처리 이후 session 설정 및 model 객체에 저장 
		// home.jsp에서 로그인 처리 해야 함 
		
		return "redirect:/home";
	}
	
	// 네이버 아이디로 로그인 처리 프로세스, 네아로 성공시 콜백 호출 메서드
	@RequestMapping(value="/naverLoginCallBack", method= {RequestMethod.GET, RequestMethod.POST})
	public void naverLoginCallBackMethod(Model model, @RequestParam String code, 
			@RequestParam String state, HttpSession session, HttpServletResponse servletResponse, UserLoginVO userLoginVO)
					throws Exception, IOException, ParseException {
		OAuth2AccessToken oauthToken;
		// 사용자 정보를 저장하기 위한 VO 선언 
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 로그인 사용자 정보를 읽어온다, String 형식의 Json 데이터
		/*
				 * { "resultcode" : "00",
				 * 	 "message" : "success",
				 *   "response" : {"id":"kesd8223", "nickname":"dev", "age":"20-29", 
				 *   	"gender":"M", "email":"kesd@naver.com", "name":"\uc2e0\ubc94\ud638"}
				 * }
		*/
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		// String 형식인 apiResult를 json형태로 변환 
		JSONParser parser = new JSONParser(); // json 파서 선언
		Object obj = parser.parse(apiResult); // apiResult를 Object형태로 변환
		JSONObject jsonObj = (JSONObject)obj; // Object 형태인 apiResult를 json Object로 변환
		
		JSONObject responseObj = (JSONObject)jsonObj.get("response");
		// json 형태의 response 값들을 VO 객체의 매개변수에 매핑하기 위한 setInstances() 메서드 호출 
		userLoginVO.setInstances(responseObj);
		userLoginVO.toString();
		
		session.setAttribute("User", userLoginVO);
		// 팝업창을 닫고 부모 창을 새로고침하는 부분
		ScriptUtils.alertAndClosingPage(servletResponse, "네이버 아이디로 로그인 하셨습니다.", "/home");
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logoutMethod(HttpSession session, HttpServletResponse response) throws Exception, IOException{
		if(session.getAttribute("User") != null) {
			session.invalidate();
		}
		
		ScriptUtils.alertAndMovePage(response, "로그아웃 되셨습니다.", "/home");
	}
}
