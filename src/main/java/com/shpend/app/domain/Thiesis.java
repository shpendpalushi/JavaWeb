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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.IndexColumn;

@Entity
public class Thiesis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OrderColumn
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="thiesis")
	private Question[] questions = new Question[10];
	@ManyToOne(cascade=CascadeType.ALL)
	private Course course;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Transient
	private String courseName;
	@Transient
	private Long tmpCourse;
	
	public Thiesis() {
		
	}

	

	public Thiesis(Long id, Question[] questions, Course course, Date createdAt, String courseName, Long tmpCourse) {
		super();
		this.id = id;
		this.questions = questions;
		this.course = course;
		this.createdAt = createdAt;
		this.courseName = courseName;
		this.tmpCourse = tmpCourse;
	}



	public Thiesis(Long id, Question[] questions, Course course, Date createdAt) {
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
	public Question[] getQuestions() {
		return questions;
	}
	public void setQuestions(Question[] q) {
		this.questions = q;
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
	

}
