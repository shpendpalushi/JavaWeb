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

import com.shpend.app.domain.AnswerThiesis;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.UserService;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
class ViewCourseExamControllerTest {
	@PersistenceContext
	EntityManager em;
	@Autowired
	TeacherRepository tr;
	@Autowired
	UserService ss;
	@Test
	void test() {
		Long courseId = 4l;
		TypedQuery<Thiesis> q = em.createQuery("Select t from Thiesis t where t.course.id=:courseId", Thiesis.class)
				.setParameter("courseId", courseId);
//		for (Thiesis t : q.getResultList()) {
//			System.out.println(t.getCreatedAt());
//		}
		assertNotNull(q.getResultList());
	}
	
	
	@Test
	void test2() {
		Long id = ss.get((long)1).getTeacher().getId();
		TypedQuery<AnswerThiesis> q = em.createQuery("Select at from AnswerThiesis at where at.thiesis.course.id=:course_id and at.thiesis.teacher.id=:t_id", AnswerThiesis.class)
				.setParameter("course_id", (long)3).setParameter("t_id", id);
		assertNotNull(q.getResultList());
	}
}
