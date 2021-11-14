package org.my.web.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.my.web.domain.NewsVO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*네이버 개발자 센터 참조*/
@Service
public class NaverNewsServiceImpl implements NaverNewsService{
	private final static String CLIENT_ID="클라이언트 아이디";
	private final static String CLIENT_SECRET="클라이언트 비밀키";
	
	@Override
	public List<NewsVO> searchNewsByKeyword(String keyword) throws Exception {
		try {
			keyword = URLEncoder.encode(keyword, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}
		
		String searchURL = "https://openapi.naver.com/v1/search/news?query="+keyword;
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
		requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);
		String responseBody = get(searchURL, requestHeaders);
		
		return mappingNewsVO(responseBody);
	}
	
	private static String get(String searchURL, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(searchURL); // connect 함수 호출 
		
		try {
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				return readBody(con.getInputStream());
			}
			else {
				return readBody(con.getErrorStream());
			}
		}
		catch(IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		}
		finally {
			con.disconnect();
		}
	}
	
	private static HttpURLConnection connect(String searchURL) {
		try {
			URL url = new URL(searchURL);
			return (HttpURLConnection)url.openConnection();
		}
		catch(MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다."+searchURL, e);
		}
		catch(IOException e) {
			throw new RuntimeException("연결 실패"+searchURL, e);
		}
	}
	
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
		try(BufferedReader lineReader = new BufferedReader(streamReader)){
			StringBuilder responseBody = new StringBuilder();
			
			String line;
			while((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
		}
		catch(IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패", e);
		}
	}
	
	// JSON Array, Object 참조 : https://mjmjmj98.tistory.com/146
	private static List<NewsVO> mappingNewsVO(String responseBody){
		try {
			JSONObject responseObject = new JSONObject(responseBody);
			JSONArray items = responseObject.getJSONArray("items");
			List<NewsVO> news = new ArrayList<>();
			
			for(int i=0; i<items.length(); ++i) {
				JSONObject itemObject = items.getJSONObject(i);
				NewsVO newsVO = new NewsVO(itemObject);
				news.add(newsVO);
			}
			return news;
		}
		catch(Exception e) {
			throw new RuntimeException("json 파싱 실패", e);
		}
	}
}
