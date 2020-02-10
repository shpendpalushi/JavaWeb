package com.shpend.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class AnswerThiesis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
//	@Length
//	private String fullAnswer;
	@OneToMany(fetch=FetchType.EAGER , mappedBy="answerThiesis")
	private List<Answer> answers = new ArrayList<>();
	@ManyToOne
	private Thiesis thiesis;
	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;
	private Short passive;
	private Short grade;
	private String result;
	
	public AnswerThiesis() {
		
	}
	
	public AnswerThiesis(Long id, List<Answer> answers, Thiesis thiesis, Student student,
			Short passive) {
		super();
		this.id = id;
		this.answers = answers;
		this.thiesis = thiesis;
		this.student = student;
		this.passive = passive;
	}
	public Short getPassive() {
		return passive;
	}
	public void setPassive(Short passive) {
		this.passive = passive;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Thiesis getThiesis() {
		return thiesis;
	}
	public void setThiesis(Thiesis thiesis) {
		this.thiesis = thiesis;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Short getGrade() {
		return grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	

}
