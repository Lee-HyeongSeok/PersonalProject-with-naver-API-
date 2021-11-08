package org.my.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String mainHomeMethod() {
		
		
		return "main/home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
	public String searchHomeMethod(@RequestParam String keyword) {
		
		System.out.println(keyword);
		return "main/home";
	}
	
}
