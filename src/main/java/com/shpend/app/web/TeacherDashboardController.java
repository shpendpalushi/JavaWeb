package com.shpend.app.web;

import java.io.IOException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherDashboardController {
	@GetMapping("/teacher/{userId}")
	  public String dashboard() throws IOException {
			return "teacher_dashboard"; 
	  }
}
