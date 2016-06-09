package com.fprochazka.drill.model.drill.question;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionFacade
{

	private QuestionRepository questionRepository;
	private DrillRepository drillRepository;

	@Autowired
	public QuestionFacade(QuestionRepository questionRepository, DrillRepository drillRepository)
	{
		this.questionRepository = questionRepository;
		this.drillRepository = drillRepository;
	}

	public Question createQuestion(String title, List<Answer> answers, UUID drillId)
	{
		Drill drill = drillRepository.findOne(drillId);
		Question question = new Question(title, answers, drill);
		questionRepository.save(question);

		return question;
	}

	public List<Question> getAllQuestionsInDrill(UUID drillId)
	{
		List<Question> questions = questionRepository.getQuestionsByDrill(drillId);

		return Collections.unmodifiableList(questions);
	}

	public Question updateQuestion(UUID questionId, String title, List<Answer> answers)
	{
		Question question = questionRepository.getQuestionById(questionId);
		question.setTitle(title);
		question.setAnswers(answers);

		questionRepository.save(question);
		return question;
	}


}
