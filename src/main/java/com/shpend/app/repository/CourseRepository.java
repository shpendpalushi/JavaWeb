package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.shpend.app.domain.Course;

@Component
public interface CourseRepository extends JpaRepository<Course, Long> {
	Course findByName(String name);
}
