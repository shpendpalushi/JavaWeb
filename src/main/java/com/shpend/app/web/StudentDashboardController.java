package com.shpend.app.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

import com.shpend.app.domain.AnswerThiesis;
import com.shpend.app.domain.Course;
//import com.shpend.app.domain.Question;
import com.shpend.app.domain.Student;
import com.shpend.app.domain.StudentCourseTaken;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.StudentRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.StudentService;
import com.shpend.app.service.UserService;

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
	@PersistenceContext
	EntityManager em;
	@Autowired
	UserService userService;
	Set<StudentCourseTaken> takenCourses = null;
	
	@GetMapping("/dashboard/{userId}")
	  public String dashboard(@PathVariable Long userId,ModelMap map, HttpServletResponse response) throws IOException {
		 
			 User user = userService.get(userId);
			 map.put("user", user);
			 map.put("id", user.getId());
			 Student student = user.getStudent();
			 map.put("student", student);
			 try {
				 System.out.println(student.getViti());
					TypedQuery<Course> q =em.createQuery("Select c from Course c where c.year <=:c_y",Course.class)
							.setParameter("c_y", student.getViti());
					List<Course> getAllCourses= q.getResultList();
					TypedQuery<StudentCourseTaken> q2 =em.createQuery("Select s from StudentCourseTaken s where s.student.id=:s_id ",StudentCourseTaken.class)
							.setParameter("s_id", user.getStudent().getId());
					List<StudentCourseTaken> resultList = q2.getResultList();
					List<Course> coursesFromSCT = getCoursesFromSCT(resultList);
					map.put("takenCourses", coursesFromSCT);
					List<Course> myCourses = new ArrayList<>();
					for (Course c: getAllCourses) {
						if(!coursesFromSCT.contains(c)) {
							myCourses.add(c);
						}
					}
				 map.put("courses", myCourses);
				 
			 }catch(NullPointerException e) {
				 return "dashboard";
			 }
			 
			 
			 map.put("student",student);
			 
			 TypedQuery<AnswerThiesis> qnew = em.createQuery("Select a from AnswerThiesis a where a.student.id=:s_id and passive=1", AnswerThiesis.class)
						.setParameter("s_id", student.getId());
			 for (AnswerThiesis a : qnew.getResultList()) {
				System.out.println(a.getGrade());
			}
			 map.put("taken_exams", qnew.getResultList());
				
			 
			 return "dashboard";
		 
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
			 Student student = user.getStudent();
			 map.put("student", student);
			 takenCourses = student.getTakenCourses();
			 
			return "update_profile";
		 }
		 
		 return "dashboard";
	}
	
	@PostMapping("/dashboard/{userId}/update_profile")
	@Modifying
	public String update_profile(@PathVariable Long userId, Student student) throws IOException {
		student.setTakenCourses(takenCourses);
		System.out.println(student.getAbout());
		System.out.println(student.getId()+"<<<<<<<<<<<>>>>>>");
		studentRepo.save(student);
		return "redirect:";
		
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
	}
	
	private List<String> getQuestionsThiesis(Thiesis thiesis){
		String questions = thiesis.getQuestions();
		String[] arrayQuestions = questions.split("\n");
		return Arrays.asList(arrayQuestions);
	}
	
	private List<Course> getCoursesFromSCT(List<StudentCourseTaken> t){
		List<Course> courses = new ArrayList<>();
		for (StudentCourseTaken studentCourseTaken : t) {
			courses.add(studentCourseTaken.getCourse());
		}
		return courses;
	}
}
