package com.fprochazka.drill.fixtures;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillRepository;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrillTestFixtures
{
	public static Drill drillPB138;
	public static Question drillPB138question1;
	public static Question drillPB138question2;
	public static Question drillPB138question3;

	public static Drill drillMB104;
	public static Question drillMB104question1;
	public static Question drillMB104question2;
	public static Question drillMB104question3;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private DrillRepository drillRepository;

	public void install()
	{
		drillPB138 = new Drill("PB138", "Znackovacie");
		drillRepository.save(drillPB138);

		drillPB138question1 = createQuestion(drillPB138, "Question 1");
		questionRepository.save(drillPB138question1);
		drillPB138question2 = createQuestion(drillPB138, "Question 1");
		questionRepository.save(drillPB138question2);
		drillPB138question3 = createQuestion(drillPB138, "Question 1");
		questionRepository.save(drillPB138question3);

		drillMB104 = new Drill("MB104", "Znackovacie");
		drillRepository.save(drillMB104);

		drillMB104question1 = createQuestion(drillMB104, "Question 1");
		questionRepository.save(drillMB104question1);
		drillMB104question2 = createQuestion(drillMB104, "Question 1");
		questionRepository.save(drillMB104question2);
		drillMB104question3 = createQuestion(drillMB104, "Question 1");
		questionRepository.save(drillMB104question3);
	}

	private Question createQuestion(Drill drill, String title)
	{
		List<Answer> answers = new ArrayList<>();
		answers.add(new Answer(true, "this is right"));
		answers.add(new Answer(false, "this is wrong"));

		return new Question(title, answers, drill);
	}

}
