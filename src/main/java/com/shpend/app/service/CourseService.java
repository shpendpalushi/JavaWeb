package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.UserRepository;


@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	UserRepository userRepo;
	
	public Course save(Course  course,Long id) {
		Course mySavedCourse = courseRepo.findByName(course.getName());
		if(mySavedCourse !=null) {
			System.out.println(mySavedCourse.getDepartament() + "++++");
			return null;
		}
			
		Optional<User> user = userRepo.findById(id);
		if(!user.isEmpty()) {
			User myUser = user.get();
			System.out.println(myUser.getTeacher()+ "************************************************************");
			course.getTeachers().add(myUser.getTeacher());
			myUser.getTeacher().getCourses().add(course);
			Date date = new Date();
			course.setCreatedAt(new Timestamp(date.getTime()));
			return courseRepo.save(course);
		}else {
			return null;
		}
		
	}
}
