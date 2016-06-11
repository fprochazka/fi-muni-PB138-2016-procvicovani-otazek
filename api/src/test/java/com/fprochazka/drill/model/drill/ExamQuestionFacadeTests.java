package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.ExamTestFixtures;
import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.question.ExamQuestionFacade;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import com.fprochazka.drill.model.exceptions.NotUniqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Michaela Bamburová on 11.06.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest(randomPort=true)
public class ExamQuestionFacadeTests extends IntegrationTestCase
{
	@Autowired
	private ExamQuestionFacade examQuestionFacade;

	@Test
	public void testUpdateExamQuestionIncreaseCorrect() throws NotFoundException, NotUniqueException
	{
		examQuestionFacade.updateExamQuestionIncreaseCorrect(ExamTestFixtures.examQuestion1.getExam().getId(), ExamTestFixtures.examQuestion1.getQuestion().getId(), 4);
	}

	@Test(expected = NotFoundException.class)
	public void testUpdateExamQuestionIncreaseCorrectWithNullExam() throws NotFoundException, NotUniqueException
	{
		Exam exam = new Exam(null, null);
		examQuestionFacade.updateExamQuestionIncreaseCorrect(exam.getId(), ExamTestFixtures.examQuestion1.getQuestion().getId(), 4);
	}
}
