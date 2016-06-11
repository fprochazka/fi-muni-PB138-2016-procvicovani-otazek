package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.QuestionFacade;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import com.fprochazka.drill.model.exceptions.NotUniqueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michaela Bamburová on 11.06.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class QuestionFacadeTests extends IntegrationTestCase
{
	@Autowired
	private QuestionFacade questionFacade;

	@Test(expected = NotFoundException.class)
	public void testCreateQuestionWithNullDrillThrowsException() throws NotFoundException
	{
		List<Answer> answers = new ArrayList<>();
		Drill drill = new Drill("PB", "drill name");
		questionFacade.createQuestion("question 1", answers, drill.getId());
	}

	@Test(expected = NotFoundException.class)
	public void testUpdateNullQuestionThrowsException() throws NotFoundException
	{

		questionFacade.updateQuestion(null, "question 1", null);
	}

	@Test
	public void testUpdateCorrectQuestion() throws NotFoundException
	{
		questionFacade.updateQuestion(DrillTestFixtures.drillMB104question1.getId(), "question 1", null);
	}
}
