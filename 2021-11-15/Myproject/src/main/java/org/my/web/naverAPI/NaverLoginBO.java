package org.my.web.naverAPI;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/* 인증 요청문을 구성하는 파라미터
 * client_id : 애플리케이션 등록 후 발급받은 클라이언트 아이디
 * response_type : 인증 과정에 대한 구분 값, oode로 값이 고정되어 있다.
 * redirect_url : 네이버 로그인 인증의 결과를 전달 받을 콜백 URL, 애플리케이션 등록 시 설정한 콜백 URL
 * state : 애플리케이션이 생성한 상태 토큰 
 * profile_api_url : 프로필 조회 API url
 */ 

public class NaverLoginBO {
	private final static String CLIENT_ID="BCjStv1q0k09bsD5HKcl";
	private final static String CLIENT_SECRET="E1plKoU6RJ";
	private final static String REDIRECT_URL="http://localhost:2020/login/naverLoginCallBack";
	private final static String SESSION_STATE="oauth_state";
	private final static String PROFILE_API_URL="https://openapi.naver.com/v1/nid/me";

	// 난수 생성 함수 
	private String generateRandomString() throws Exception {
		return UUID.randomUUID().toString();
	}
	
	// http session에 난수 값 저장
	private void setSession(HttpSession session, String state) throws Exception {
		try {
			session.setAttribute(SESSION_STATE, state);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// http session에서 난수 값 가져오기 
	private String getSession(HttpSession session) throws Exception {
		return (String)session.getAttribute(SESSION_STATE);
	}
	
	// 네이버 아이디로 인증 URL 생성 메서드
	public String getAuthorizationUrl(HttpSession session) throws Exception {
		// 세션 유효성 검증을 위한 난수 생성
		String state = generateRandomString();
		
		// 생성한 난수 값을 세션에 저장
		setSession(session, state);
		
		// scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL)
				.state(state) // 앞서 생성한 난수 값을 인증 URL 생성 시 사용 
				.build(NaverLoginAPI.instance());
		return oauthService.getAuthorizationUrl();
	}
	
	// 네이버 아이디로 로그인 Callback 처리 및 Access Token 획득 메서드
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws Exception, IOException{
		// 현재 사용자 세션에 난수 값을 가져온다.
		String sessionState = getSession(session);
		
		// Callback으로 전달 받은 세션 검증용 난수 값과 세션에 저장된 난수 값이 일치하는지 확인 
		if(StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(REDIRECT_URL)
					.state(state)
					.build(NaverLoginAPI.instance());
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		
		return null;
	}
	
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException{
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(REDIRECT_URL)
				.build(NaverLoginAPI.instance());
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		return response.getBody();
	}
}
