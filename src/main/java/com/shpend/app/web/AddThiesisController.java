package com.shpend.app.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.service.ThiesisService;


@Controller
public class AddThiesisController {
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	CourseRepository courseRepo;
	
	  @GetMapping("/add_thiesis")
	  public String register (ModelMap model) {
		List<String> courses = courseNames(courseRepo.findAll());
		model.put("courses", courses);
	    model.put("thiesis", new Thiesis());
	    return "add_thiesis";
	  }
	  
	  @PostMapping("/add_thiesis")
	  public String registerThiesis (Thiesis thiesis) {
	    Thiesis saveThiesis = thiesisService.save(thiesis, thiesis.getCourseName());
	    
	    return "redirect:/login";
	  }
	  
	  private List<String> courseNames(List<Course> courses)
	  {
		  List<String> courseNames = new ArrayList<>();
		  for(Course c : courses)
			  courseNames.add(c.getName());
		  return courseNames;
	  }

}
