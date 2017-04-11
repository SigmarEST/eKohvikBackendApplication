package com.coffemachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping({
		"/",
		"/items",
		"/users",
		"/stations",
		"/purchases",
		"/cards"	
	})
	
	public String index(){
		return "index";
	}

}
