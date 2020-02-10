package com.shpend.app.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String dega;
	private Short viti;
	private String niveli;
	private String hobbies;
	private String about;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true, fetch=FetchType.EAGER, mappedBy="course")
	private Set<StudentCourseTaken> takenCourses;

	public User getUser() {
		return user;
	}
	
	
	
	public Student(Long id, String dega, Short viti, String niveli, String hobbies, String about, User user,
			Date createdAt, Set<StudentCourseTaken> takenCourses) {
		super();
		this.id = id;
		this.dega = dega;
		this.viti = viti;
		this.niveli = niveli;
		this.hobbies = hobbies;
		this.about = about;
		this.user = user;
		this.createdAt = createdAt;
		this.takenCourses = takenCourses;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((dega == null) ? 0 : dega.hashCode());
		result = prime * result + ((hobbies == null) ? 0 : hobbies.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((niveli == null) ? 0 : niveli.hashCode());
		result = prime * result + ((takenCourses == null) ? 0 : takenCourses.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((viti == null) ? 0 : viti.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (dega == null) {
			if (other.dega != null)
				return false;
		} else if (!dega.equals(other.dega))
			return false;
		if (hobbies == null) {
			if (other.hobbies != null)
				return false;
		} else if (!hobbies.equals(other.hobbies))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (niveli == null) {
			if (other.niveli != null)
				return false;
		} else if (!niveli.equals(other.niveli))
			return false;
		if (takenCourses == null) {
			if (other.takenCourses != null)
				return false;
		} else if (!takenCourses.equals(other.takenCourses))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (viti == null) {
			if (other.viti != null)
				return false;
		} else if (!viti.equals(other.viti))
			return false;
		return true;
	}



	public void setUser(User user) {
		this.user = user;
	}

	public Student()
	{
		
	}
	
	public Student(Long id)
	{
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Short getViti() {
		return viti;
	}

	public void setViti(Short viti) {
		this.viti = viti;
	}

	public String getDega() {
		return dega;
	}

	public void setDega(String dega) {
		this.dega = dega;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getNiveli() {
		return niveli;
	}

	public void setNiveli(String niveli) {
		this.niveli = niveli;
	}

	public Set<StudentCourseTaken> getTakenCourses() {
		return takenCourses;
	}

	public void setTakenCourses(Set<StudentCourseTaken> takenCourses) {
		this.takenCourses = takenCourses;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
