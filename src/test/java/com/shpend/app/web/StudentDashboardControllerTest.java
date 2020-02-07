package com.shpend.app.web;

import static org.junit.jupiter.api.Assertions.*;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shpend.app.domain.Course;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.TeacherRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
class StudentDashboardControllerTest {
	@PersistenceContext
	EntityManager em;
	@Autowired
	TeacherRepository tr;

	@Test
	void test() {
		Short student_year = Short.parseShort("3");
		TypedQuery<Course> q =em.createQuery("Select c from Course c where c.year <=:c_y",Course.class)
				.setParameter("c_y", student_year);
		
		for (Course c: q.getResultList()) {
			System.out.println(c.getName());
		}
				
		assertNotNull(q.getResultList());
	}
	
	@Test
	void test2(){
		Long teacherId = 3l;
		TypedQuery<Thiesis> q = em.createQuery("Select t from Thiesis t where t.active=1 and t.course.id=:teacherId", Thiesis.class)
				.setParameter("teacherId", teacherId);
		assertNotNull(q.getResultList());
	}

//	@Test
//	void test3() {
//		TypedQuery<Question> q = em.createQuery("Select q from Question q where q.thiesis.id=6", Question.class);
//		for (Question ques : q.getResultList()) {
//			System.out.println(ques.getQuestion());
//		}
//		assertNotNull(q.getResultList());
//	}
}
