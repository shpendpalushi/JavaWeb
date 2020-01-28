package com.shpend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.StudentCourseTaken;

public interface StudentCourseTakenRepository extends JpaRepository<StudentCourseTaken, Long>{

}
