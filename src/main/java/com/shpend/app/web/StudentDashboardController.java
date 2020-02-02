package com.shpend.app.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Student;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.StudentRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.StudentService;

@Controller
public class StudentDashboardController {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	private Course myCourse;
	
	@GetMapping("/dashboard/{userId}")
	  public String dashboard(@PathVariable Long userId,ModelMap map, HttpServletResponse response) throws IOException {
		 Optional<User> userOptional = 	userRepo.findById(userId);
		 if(userOptional.isPresent())
		 {
			 User user = userOptional.get();
			 map.put("user", user);
			 map.put("id", user.getId());
			 Student student = user.getStudent();
			 map.put("student", student);
			 try {
				 System.out.println(student.getViti());
				 List<Course> findByYear = courseRepo.findByYear(student.getViti());
				 map.put("courses", findByYear);
				 
			 }catch(NullPointerException e) {
				 return "dashboard";
			 }
			 
			 
			 map.put("student",student);
			 
			 return "dashboard";
			 
		 }else {
		      response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + userId + " was not found");
		      return "error";
		    }
		 
	  }
	
	@GetMapping("/dashboard/{userId}/thiesis/{courseId}")
	public String enterExam(ModelMap model, @PathVariable Long courseId, @PathVariable Long userId) {
		Optional<Course> course = courseRepo.findById(courseId);
		if(course.isPresent()) {
			myCourse = course.get();
			model.put("course", myCourse);
			Iterator<Thiesis> thiesis = myCourse.getThiesis().iterator();
			model.put("thiesis", thiesis.next());
		}
		return "perform_exam";
	}
	
	@PostMapping("/dashboard/{userId}/thiesis/{thiesisId}")
	public String submitAnswers(@PathVariable Long userId) {
		
		return "redirect:dashboard/"+ userId;
	}
	
	@GetMapping("/dashboard/{userId}/update_profile")
	public String update_profile(@PathVariable long userId,ModelMap map, HttpServletResponse response) throws IOException {
		 Optional<User> userOptional = 	userRepo.findById(userId);
		 if(userOptional.isPresent())
		 {
			 
			 User user = userOptional.get();
			 map.put("user", user);
			 map.put("id", user.getId());
			 map.put("hobbies", new String());
			 map.put("about", new String());
			 map.put("student", user.getStudent());
			return "update_profile";
		 }
		 
		 return "dashboard";
	}
	
	@PostMapping("/dashboard/{userId}/update_profile")
	@Modifying
	public String update_profile(@PathVariable Long userId, Student student) throws IOException {
		System.out.println(student.getAbout());
		studentRepo.save(student);
		return "redirect:";
		
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
}
