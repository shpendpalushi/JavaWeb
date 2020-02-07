package com.shpend.app.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shpend.app.domain.Answer;
import com.shpend.app.domain.Course;
import com.shpend.app.domain.Student;
import com.shpend.app.domain.StudentCourseKey;
import com.shpend.app.domain.StudentCourseTaken;
//import com.shpend.app.domain.Question;
import com.shpend.app.domain.Thiesis;
import com.shpend.app.domain.User;
import com.shpend.app.repository.AnswerRepository;
import com.shpend.app.repository.CourseRepository;
import com.shpend.app.repository.StudentCourseTakenRepository;
import com.shpend.app.repository.StudentRepository;
import com.shpend.app.repository.UserRepository;
import com.shpend.app.service.StudentService;
import com.shpend.app.service.ThiesisService;
import com.shpend.app.service.UserService;

@Controller
public class PerformExamController {
	
	@Autowired
	UserRepository userRepo;
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	StudentService studentService;
	@Autowired
	ThiesisService thiesisService;
	@Autowired
	AnswerRepository answerRepo;
	@Autowired
	UserService userService; 
	@PersistenceContext
	EntityManager em;
	@Autowired
	StudentCourseTakenRepository sRepo;
	
	@GetMapping("/dashboard/{userId}/thiesis/{courseId}")
	public String enterExam(ModelMap model, @PathVariable Long courseId, @PathVariable Long userId) {
		TypedQuery<Thiesis> q = em.createQuery("Select t from Thiesis t where t.active=1 and t.course.id=:courseId", Thiesis.class)
				.setParameter("courseId", courseId);
		
		if(q.getResultList()!=null) {
			List<Thiesis> resultList = q.getResultList();
			
			Thiesis thiesis = resultList.get(0);
			
			List<String> questionsThiesis = getQuestionsThiesis(thiesis);
			System.out.println(questionsThiesis.size());
			ArrayList<Answer> answers = new ArrayList<>();
			for (String s : questionsThiesis) {
				answers.add(new Answer());
			}
			model.put("questions", questionsThiesis);
			thiesis.setAnswer(answers);
			model.put("thiesis", thiesis);
			//TypedQuery<Question> ques = em.createQuery("Select q from Question q where q.thiesis.id=:thiesisId", Question.class)
				//	.setParameter("thiesisId", thiesis.getId());
			
			
			
			
		}
		return "perform_exam";
	}
	
	@PostMapping("/dashboard/{userId}/thiesis/{thiesisId}")
	public String submitAnswers(@PathVariable Long userId, @PathVariable Long thiesisId,
			@ModelAttribute(value="thiesis")Thiesis thiesis ) {
			Student studentByUser = getStudentByUser(userId);
			for (Answer a : thiesis.getAnswer()) {
				a.setThiesis(thiesis);
				a.setStudent(studentByUser);
				a.setPassive((short)0);
				answerRepo.save(a);
			}
			System.out.println(thiesis.getTmpCourse());
			thiesisService.save(thiesis);
			for (Answer a : thiesis.getAnswer()) {
				
				System.out.println(a.getAnswer());
			}
			Course course = thiesis.getCourse();
			StudentCourseKey k = new StudentCourseKey(studentByUser.getId(), course.getId());
			StudentCourseTaken st = new StudentCourseTaken(k, studentByUser, course);
			st.setCompleted((short)1);
			sRepo.save(st);
			
		return "redirect:/dashboard/"+ userId;
	}
	
	private List<String> getQuestionsThiesis(Thiesis thiesis){
		String questions = thiesis.getQuestions();
		String[] arrayQuestions = questions.split("\n");
		 List<String> asList = Arrays.asList(arrayQuestions);
		for (String string : asList) {
			System.out.println(string);
			
		}
		return asList;
	}
	
	private Student getStudentByUser(Long userId) {
		return userService.get(userId).getStudent();
	}

}
