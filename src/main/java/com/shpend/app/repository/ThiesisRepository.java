package com.shpend.app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.Thiesis;
public interface ThiesisRepository extends JpaRepository<Thiesis, Long> {
	List<Thiesis> findByTeacher(Teacher teacher);
}
