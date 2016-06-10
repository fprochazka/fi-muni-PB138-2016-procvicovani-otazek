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
import com.mongodb.DB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class Pb138drillApplicationTests
{

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	DrillRepository drillRepository;

	@Autowired
	ExamQuestionRepository examQuestionRepository;

	@Autowired
	ExamRepository examRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Before
	public void clearDatabase()
	{
		DB db = mongoTemplate.getDb();
		db.getCollectionNames()
			.stream()
			.filter(collectionName -> !collectionName.startsWith("system."))
			.forEach(collectionName -> {
				mongoTemplate.remove(new Query(), collectionName);
			});
	}

	@Test
	public void testFindOne()
	{
		createAndPersistFixtures();

		List<Question> question = (List<Question>) questionRepository.findAll();
		assertEquals(3, question.size());
	}

	@Test
	public void testFindDrill()
	{
		Drill drill = createAndPersistFixtures();

		List<Question> questions = (List<Question>) questionRepository.findAll();
		assertEquals(questions, questionRepository.getQuestionsByDrill(drill.getId()));
	}

	private Drill createAndPersistFixtures()
	{
		Student student = new Student(123456, "mail@mail.com", "hash");
		studentRepository.save(student);

		Drill drill = new Drill("PB138", "Znackovacie");
		drillRepository.save(drill);

		Question question1 = createQuestion(drill, "Question 1");
		questionRepository.save(question1);
		Question question2 = createQuestion(drill, "Question 1");
		questionRepository.save(question2);
		Question question3 = createQuestion(drill, "Question 1");
		questionRepository.save(question3);

		Exam exam = new Exam(drill, student);
		examRepository.save(exam);
		ExamQuestion examQuestion1 = new ExamQuestion(question1, exam, 2, 3);
		examQuestionRepository.save(examQuestion1);
		ExamQuestion examQuestion2 = new ExamQuestion(question2, exam, 1, 0);
		examQuestionRepository.save(examQuestion2);

		return drill;
	}

	private Question createQuestion(Drill drill, String title)
	{
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(true, "this is right"));
		answers.add(new Answer(false, "this is wrong"));

		return new Question(title, answers, drill);
	}

}
