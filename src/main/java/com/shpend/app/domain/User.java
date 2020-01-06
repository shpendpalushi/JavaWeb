package com.shpend.app.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.OneToOne;

import com.shpend.app.security.Authority;


@Entity
@Table(name="users")

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String name;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER , mappedBy="user")
	private Set<Authority> authorities = new HashSet<>();
	private Long year;
//	private Authority authority;
	private String authority;
	private Integer completedInfo;
	@OneToOne(mappedBy="user")
	private Teacher teacher;
	@OneToOne(mappedBy="user")
	private Student student;
	
	
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public User() {
		
	}
	
	public User(Long id, String username, String password, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
		
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("Id->"+ getId()+",Name->" + getName() + ", Username->" +
				getUsername() +",Password->"+getPassword()+",Authorities->" + getAuthorities());
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority_property) {
		this.authority = authority_property;
	}

	public Integer getCompletedInfo() {
		return completedInfo;
	}

	public void setCompletedInfo(Integer completedInfo) {
		this.completedInfo = completedInfo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
