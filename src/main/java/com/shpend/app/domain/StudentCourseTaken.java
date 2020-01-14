package com.shpend.app.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class StudentCourseTaken {
	
	@EmbeddedId
	StudentCourseKey id;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name="student_id", referencedColumnName="id")
	private Student student;
	
	@ManyToOne
	@MapsId("id")
	@JoinColumn(name="course_id", referencedColumnName="id")
	private Course course;
	
	private Integer completed;
	private Integer result;
	
	public StudentCourseTaken() {
		
	}

	public StudentCourseTaken(StudentCourseKey id, Student student, Course course, Integer completed, Integer result) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.completed = completed;
		this.result = result;
	}



	public StudentCourseKey getId() {
		return id;
	}

	public void setId(StudentCourseKey id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	
}
