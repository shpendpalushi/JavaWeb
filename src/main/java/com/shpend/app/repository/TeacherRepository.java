package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.Teacher;



public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	
}
