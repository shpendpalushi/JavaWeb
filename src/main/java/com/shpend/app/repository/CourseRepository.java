package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Course findByName(String name);
}
