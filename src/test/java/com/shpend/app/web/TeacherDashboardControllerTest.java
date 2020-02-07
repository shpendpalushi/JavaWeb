package com.shpend.app.web;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shpend.app.domain.Answer;
import com.shpend.app.domain.Course;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.ThiesisService;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
class TeacherDashboardControllerTest {
	@PersistenceContext
	EntityManager em;
	@Autowired
	TeacherRepository tr;
	@Autowired
	TeacherService ts;
	@Autowired
	ThiesisService thiesisService;
	
	
	@Test
	void test() {
		Optional<Teacher> c = tr.findById(Long.parseLong("2"));
		if(c.isPresent()) {
			Teacher teacher = c.get();
			TypedQuery<Course> query = 
			         em.createQuery("select c from Course c join c.teachers t where t.id=:teacher_id", Course.class)
			         .setParameter("teacher_id", teacher.getId());
			List<Course> s = query.getResultList();
			for (Course course : s) {
				System.out.println(course.getName());
			}
			
			
		}
		 
	}
	
	@Test
	
	void test2() {
		Short passive =(short)1;
		Long id = (long)2;
		Teacher t = ts.findById(id);
		Long s_id = (long)6;
		Thiesis th = thiesisService.findById(5l); 
		TypedQuery<Answer> q = em.createQuery("select a from Answer a where a.thiesis.teacher.id=:teacher_id and a.thiesis.id=:thiesis_id and a.student.id=:s_id and passive=:passive"
				, Answer.class)
				.setParameter("teacher_id", t.getId()).setParameter("thiesis_id", th.getId()).setParameter("s_id", s_id).setParameter("passive", passive);
		for (Answer a : q.getResultList()) {
			System.out.println(a.getAnswer());
		}
		assertNotNull(q);
	}
	

}
