package com.shpend.app.web;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shpend.app.domain.Answer;
import com.shpend.app.domain.AnswerThiesis;
import com.shpend.app.domain.Teacher;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.TeacherRepository;
import com.shpend.app.repository.ThiesisRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.CourseService;
import com.shpend.app.service.TeacherService;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;

@Controller
public class ViewCourseExamsController {
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	@Autowired
	HttpServletRequest req;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	TeacherService teacherService;
	@Autowired
	ThiesisRepository thiesisRepo;
	@PersistenceContext
	EntityManager em;
	
	@GetMapping("/teacher/{userId}/view_course_exam/{courseId}")
	public String getAllExams(@PathVariable Long userId, @PathVariable Long courseId, ModelMap map) {
//		TypedQuery<Answer> q = em.createQuery("select a from Answer a where a.thiesis.teacher.id=:teacher_id and a.thiesis.id=:thiesis_id and a.student.id=:s_id and passive=:passive"
//				, Answer.class)
//				.setParameter("teacher_id", t.getId()).setParameter("thiesis_id", th.getId()).setParameter("s_id", s_id).setParameter("passive", passive);
		
		TypedQuery<Thiesis> q = em.createQuery("Select t from Thiesis t where t.course.id=:courseId", Thiesis.class)
				.setParameter("courseId", courseId);
		map.put("id",userId);
		map.put("all_thiesis", q.getResultList());
		for (Thiesis t : q.getResultList()) {
			System.out.println(t.getCreatedAt());
		}
		return "view_course_thiesis";
		
	}

	@GetMapping("/teacher/{userId}/view_course_exam/{courseId}/view_answers/{thiesisId}")
	public String viewAnswers(@PathVariable Long userId, @PathVariable Long courseId,@PathVariable Long thiesisId, ModelMap model){
		model.put("id", userId);
		Thiesis thiesis = thiesisRepo.getOne(thiesisId);
		model.put("thiesis",thiesis);
		Teacher teacher = getTeacher(userId);
		TypedQuery<AnswerThiesis> q = em.createQuery("Select at from AnswerThiesis at where at.thiesis.course.id=:course_id and at.thiesis.teacher.id=:t_id and passive=0", AnswerThiesis.class)
				.setParameter("course_id", courseId).setParameter("t_id", teacher.getId());
		
		TypedQuery<AnswerThiesis> q2 = em.createQuery("Select at from AnswerThiesis at where at.thiesis.course.id=:course_id and at.thiesis.teacher.id=:t_id and passive=1", AnswerThiesis.class)
				.setParameter("course_id", courseId).setParameter("t_id", teacher.getId());
		model.put("controlled_answers", q2.getResultList());
		model.put("all_answers", q.getResultList());
		return "view_answers";
	}

	private Teacher getTeacher(Long userId) {
		User user = userService.get(userId);
		return user.getTeacher();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
