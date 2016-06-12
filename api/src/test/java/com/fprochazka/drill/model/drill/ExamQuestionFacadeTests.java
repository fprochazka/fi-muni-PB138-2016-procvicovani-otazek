package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.ExamTestFixtures;
import com.fprochazka.drill.model.drill.question.QuestionNotFoundException;
import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.ExamNotFoundException;
import com.fprochazka.drill.model.exam.question.ExamQuestionFacade;
import com.fprochazka.drill.model.exam.question.ExamQuestionNotUniqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest(randomPort=true)
public class ExamQuestionFacadeTests extends IntegrationTestCase
{
	@Autowired
	private ExamQuestionFacade examQuestionFacade;

	@Test
	public void testUpdateExamQuestionIncreaseCorrect() throws ExamNotFoundException, QuestionNotFoundException, ExamQuestionNotUniqueException
	{
		examQuestionFacade.updateExamQuestionIncreaseCorrect(ExamTestFixtures.examQuestion1.getExam().getId(), ExamTestFixtures.examQuestion1.getQuestion().getId(), 4);
	}

	@Test(expected = ExamNotFoundException.class)
	public void testUpdateExamQuestionIncreaseCorrectWithNullExam() throws ExamNotFoundException, QuestionNotFoundException, ExamQuestionNotUniqueException
	{
		Exam exam = new Exam(null, null);
		examQuestionFacade.updateExamQuestionIncreaseCorrect(exam.getId(), ExamTestFixtures.examQuestion1.getQuestion().getId(), 4);
	}
}
