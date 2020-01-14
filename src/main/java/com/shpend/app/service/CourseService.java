package com.shpend.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Course;
import com.shpend.app.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepo;
	
	public Course save(Course  course) {
		Course mySavedCourse = courseRepo.findByName(course.getName());
		if(mySavedCourse !=null)
			return null;
		return courseRepo.save(course);
	}
}
