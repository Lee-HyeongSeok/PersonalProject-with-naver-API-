package org.my.web.controller;

import java.util.List;

import org.my.web.domain.NewsVO;
import org.my.web.service.NaverNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private NaverNewsService naverNewsService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String mainHomeMethod() {
		
		
		return "main/home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String searchHomeMethod(@RequestParam String keyword) throws Exception {
		System.out.println(keyword);
		List<NewsVO> news = naverNewsService.searchNewsByKeyword(keyword);
		
		for(int i=0; i<news.size(); ++i) {
			System.out.println(news.get(i).toString());
		}
		return "main/home";
	}
	
}
