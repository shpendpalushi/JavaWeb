package com.shpend.app.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Student;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.UserRepository;

@Controller
public class StudentDashboardController implements RegisterRole {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	CourseRepository courseRepo;
	private Course myCourse;
	
	@GetMapping("/dashboard/{userId}")
	  public String dashboard(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException {
		 Optional<User> userOptional = 	userRepo.findById(userId);
		 if(userOptional.isPresent())
		 {
			 
			 User user = userOptional.get();
			 map.put("user", user);
			 map.put("id", user.getId());
			 Student student = user.getStudent();
			 List<Course> findByYear = courseRepo.findByYear(student.getViti());
			 map.put("courses", findByYear);
			 map.put("student",student);
			 
			 
		 }else {
		      response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + userId + " was not found");
		      return "error";
		    }
		 
		 return "dashboard";
	  }
	
	@GetMapping("/dashboard/{userId}/thiesis/{courseId}")
	public String enterExam(ModelMap model, @PathVariable Long courseId, @PathVariable Long userId) {
		Optional<Course> course = courseRepo.findById(courseId);
		if(course.isPresent()) {
			myCourse = course.get();
			model.put("course", myCourse);
			Thiesis thiesis = myCourse.getThiesis().get(0);
			model.put("thiesis", thiesis);
		}
		return "perform_exam";
	}
	
	@PostMapping("/dashboard/{userId}/thiesis/{}")
	public String submitAnswers(@PathVariable Long userId) {
		
		return "redirect:dashboard/"+ userId;
	}
}
