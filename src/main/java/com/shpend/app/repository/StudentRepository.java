package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.Student;
import com.shpend.app.domain.User;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
