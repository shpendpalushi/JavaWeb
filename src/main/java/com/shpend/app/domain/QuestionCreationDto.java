package com.shpend.app.domain;

import java.util.ArrayList;
import java.util.List;

public class QuestionCreationDto {
	private List<Question> questions = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	
	public QuestionCreationDto() {
		
	}
	public void addQuestion(Question q) {
		this.questions.add(q);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
