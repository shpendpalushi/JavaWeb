package com.shpend.app.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Question;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.ThiesisRepository;

@Service
public class ThiesisService {
	@Autowired
	QuestionService questionService;
	@Autowired
	ThiesisRepository thiesisRepo;
	@Autowired
	CourseRepository courseRepo;
	public Thiesis save(Thiesis thiesis, String name)
	{
		Course course =courseRepo.findByName(name);
		if(course!=null) {
			thiesis.setCourse(course);
			for(Question question : thiesis.getQuestions()) {
				question.setThiesis(thiesis);
				questionService.save(question);
			}
			Date date = new Date();
			thiesis.setCreatedAt(new Timestamp(date.getTime()));
			
			return thiesisRepo.save(thiesis);
			
		}
		else {
			System.out.println("Couldn't save the thiesis");
			return null;
		}
		
	}

}
