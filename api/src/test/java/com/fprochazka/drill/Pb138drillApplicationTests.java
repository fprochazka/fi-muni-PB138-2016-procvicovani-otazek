package com.fprochazka.drill;

import com.fprochazka.drill.config.ApplicationConfig;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionRepository;
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

	@Before
	public void init(){
		questionRepository.save(createQuestion());
	}

	public Question createQuestion() {

		String title = "New question";
		List<Answer> answers = new ArrayList<>();

		answers.add(new Answer(true, "this is right"));
		answers.add(new Answer(false, "this is wrong"));

		Drill drill = new Drill("drill 1");

		return new Question(title, answers, drill);
	}


	@Test
	public void findAll() {
		List<Question> question = (List<Question>) questionRepository.findAll();
		assertEquals(1, question.size());
	}

}
