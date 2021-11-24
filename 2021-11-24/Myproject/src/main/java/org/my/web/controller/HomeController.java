package org.my.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.my.web.domain.NewsVO;
import org.my.web.service.NaverNewsService;
import org.my.web.service.YouTubeSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
	@Autowired
	private NaverNewsService naverNewsService;
	
	@Autowired
	private YouTubeSearchService youtubeSearchService;
	
	private String display = null;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String mainHomeMethod() {
		
		
		return "main/home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String searchHomeMethod(@RequestParam String keyword, Model model) throws Exception, IOException {
		List<NewsVO> newsList = new ArrayList<>(); // 검색된 뉴스의 내용을 담는 리스트 선언
		Map<String, List<NewsVO>> searchResults = naverNewsService.searchNewsByKeyword(keyword);
		
		for(Map.Entry<String, List<NewsVO>> news : searchResults.entrySet()) {
			display = news.getKey(); // 검색 결과 개수
			newsList = news.getValue(); // 검색된 뉴스의 내용들
		}
		
		model.addAttribute("display", display);
		model.addAttribute("newsList", newsList);
		model.addAttribute("keyword", keyword);
		
		// YouTube Search
		youtubeSearchService.searchWithYouTube();
		
		return "main/home";
	}
	
}
