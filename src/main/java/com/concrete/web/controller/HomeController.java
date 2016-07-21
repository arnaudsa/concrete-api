package com.concrete.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/home")
	public String getPageJSP() {
		return "home";
	}

	@RequestMapping(value = "/index")
	public String getPageHtml() {
		return "index";
	}
}
