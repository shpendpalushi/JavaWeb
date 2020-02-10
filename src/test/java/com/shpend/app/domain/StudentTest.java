package com.shpend.app.domain;

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

import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.ThiesisService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@EntityScan("com.shpend")
class StudentTest {
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
		Long id = 6l;
		TypedQuery<AnswerThiesis> q = em.createQuery("Select a from AnswerThiesis a where a.student.id=:s_id and passive=1", AnswerThiesis.class)
				.setParameter("s_id", id);
		for (AnswerThiesis a : q.getResultList()) {
			System.out.println(a.getThiesis().getCourse().getName()+"got the result" + a.getGrade() + " with the result " + a.getResult());
		}
		assertNotNull(q.getResultList());
		
	}

}
