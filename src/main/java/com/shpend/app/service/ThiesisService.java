package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Question;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.QuestionRepository;
import com.shpend.app.repository.ThiesisRepository;

@Service
public class ThiesisService {
	@Autowired
	QuestionService questionService;
	@Autowired
	ThiesisRepository thiesisRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	QuestionRepository questionRepo;
	public Thiesis save(Thiesis thiesis, Long id,Question[] ques)
	{
		Optional<Course> course =courseRepo.findById(id);
		if(course.isPresent()) {
			Course myCourse = course.get();
			thiesis.setCourse(myCourse);
			thiesis.setQuestions(ques);
			for(int i = 0 ; i<ques.length; i++) {
				questionRepo.save(ques[i]);
			}
			System.out.println(ques.length + "***********************************************************");
			Date date = new Date();
			thiesis.setCreatedAt(new Timestamp(date.getTime()));
			Thiesis save = thiesisRepo.save(thiesis);
			return save;
			
			
		}
		else {
			System.out.println("Couldn't save the thiesis");
			return null;
		}
		
	}

}
