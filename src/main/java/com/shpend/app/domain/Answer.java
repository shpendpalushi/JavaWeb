package com.shpend.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String answer;
	@ManyToOne
	private Student student;
//	@ManyToOne(cascade=CascadeType.ALL)
//	private Question question;
	@ManyToOne()
	private Thiesis thiesis;
	private Short passive;
	public Answer() {
		
	}
	
		
	public Answer(Long id, String answer, Student student/**, Question question**/) {
		super();
		this.id = id;
		this.answer = answer;
		this.student = student;
//		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
//	public Question getQuestion() {
//		return question;
//	}
//	public void setQuestion(Question question) {
//		this.question = question;
//	}


	public Thiesis getThiesis() {
		return thiesis;
	}


	public void setThiesis(Thiesis thiesis) {
		this.thiesis = thiesis;
	}


	public Short getPassive() {
		return passive;
	}


	public void setPassive(Short passive) {
		this.passive = passive;
	}

}
