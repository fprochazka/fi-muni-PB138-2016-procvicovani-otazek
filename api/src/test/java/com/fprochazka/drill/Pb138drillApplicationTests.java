package com.fprochazka.drill;

import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillRepository;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionRepository;
import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.ExamRepository;
import com.fprochazka.drill.model.exam.question.ExamQuestion;
import com.fprochazka.drill.model.exam.question.ExamQuestionRepository;
import com.fprochazka.drill.model.student.Student;
import com.fprochazka.drill.model.student.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
public class Pb138drillApplicationTests {


	@Autowired
	QuestionRepository questionRepository;
	StudentRepository studentRepository;
	DrillRepository drillRepository;
	ExamQuestionRepository examQuestionRepository;
	ExamRepository examRepository;

	@Before
	public void init(){
		questionRepository.save(createQuestion());
		studentRepository.save(createStudent());
		drillRepository.save(createDrill());
		examQuestionRepository.save(createExamQuestion());
		examRepository.save(createExam());

	}

	public Question createQuestion() {

		String title = "New question";
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(true, "this is right"));
		answers.add(new Answer(false, "this is wrong"));
		Drill drill = createDrill();

		return new Question(title, answers, drill);
	}

	public Drill createDrill() {
		return new Drill("drill 1");
	}

	public ExamQuestion createExamQuestion() {
		Question question = createQuestion();
		Exam exam = createExam();
		int correct = 2;
		int wrong = 3;
		return new ExamQuestion(question, exam, correct, wrong);
	}

	public Student createStudent() {
		return new Student(123456, "mail@mail.com", "hash");
	}

	public Exam createExam() {
		Drill drill = createDrill();
		Student student = createStudent();
		return new Exam(drill, student);
	}




	@Test
	public void testFindOne() {
		List<Question> question = (List<Question>) questionRepository.findAll();
		assertEquals(1, question.size());
	}

	@Test
	public void testFindDrill() {
		List<Question> questions = (List<Question>) questionRepository.findAll();
		assertEquals(questions, questionRepository.getQuestionsByDrill(createDrill().getId()));
	}
}
