package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.IntegrationTestCase;
import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.fixtures.DrillTestFixtures;
import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebIntegrationTest(randomPort=true)
public class QuestionsRepositoryTests extends IntegrationTestCase
{

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	public void testFindAll()
	{
		List<Question> question = (List<Question>) questionRepository.findAll();
		assertEquals(6, question.size());
	}

	@Test
	public void testFindPB138Questions()
	{
		List<Question> drillQuestions = questionRepository.getQuestionsByDrill(DrillTestFixtures.drillPB138.getId());
		assertEquals(3, drillQuestions.size());
	}

}
