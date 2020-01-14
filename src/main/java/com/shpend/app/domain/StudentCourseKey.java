package com.shpend.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentCourseKey implements Serializable{
	
	private static final long serialVersionUID = 819015237657421374L;
	
	@Column(name="student_id")
	private Long studentId;
	@Column(name="course_id")
	private Long courseId;
	
	public StudentCourseKey() {
		
	}

	public StudentCourseKey(Long studentId, Long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
