package com.shpend.app.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shpend.app.security.Authority;

@Entity
@Table(name="teacher")
public class Teacher extends Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String departament;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	  name = "teaches_courses", 
	  joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName="id"), 
	  inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName="id"))
	private Set<Course> courses;
	
	

	public Teacher(Long id, String departament, User user, Date createdAt, Set<Course> courses) {
		super();
		this.id = id;
		this.departament = departament;
		this.user = user;
		this.createdAt = createdAt;
		this.courses = courses;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Teacher()
	{
		
	}
	
	public Teacher(Long id,String departament)
	{
		this.id = id;
		this.departament = departament;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
