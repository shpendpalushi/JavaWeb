package com.shpend.app.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

@Entity
public class Thiesis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Length(max=10000)
	String questions;
	@ManyToOne(cascade=CascadeType.ALL)
	private Course course;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	private Short active;
	@Transient
	private String courseName;
//	@OneToMany(fetch=FetchType.EAGER , mappedBy="thiesis")
//	private List<Question> question = new ArrayList<>();
	@OneToMany
	private List<Answer> answer = new ArrayList<>();
	
	@Transient
	private Long tmpCourse;
	@ManyToOne(cascade = {CascadeType.ALL})
	 @JoinColumn(name="teacher_id")
	private Teacher teacher;
	
	public Thiesis() {
		
	}
	
	public Thiesis(Long id, String questions, Course course, Date createdAt,
		String courseName, Long tmpCourse) {
		super();
		this.id = id;
		this.questions = questions;
		this.course = course;
		this.createdAt = createdAt;
		this.courseName = courseName;
		this.tmpCourse = tmpCourse;
	}




	public Thiesis(Long id, String questions, Course course, Date createdAt) {
		super();
		this.id = id;
		this.questions = questions;
		this.course = course;
		this.setCreatedAt(createdAt);
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}







	public Long getTmpCourse() {
		return tmpCourse;
	}



	public void setTmpCourse(Long tmpCourse) {
		this.tmpCourse = tmpCourse;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Short getActive() {
		return active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

//	public List<Question> getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(List<Question> question) {
//		this.question = question;
//	}
	

}
