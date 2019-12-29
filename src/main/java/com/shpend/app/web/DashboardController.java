package com.shpend.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public String rootView() {
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
