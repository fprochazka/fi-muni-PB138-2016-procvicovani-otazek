package com.fprochazka.drill.model.drill.question;

import com.fprochazka.drill.model.drill.answer.Answer;
import com.fprochazka.drill.model.drill.Drill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionFacade
{

	private QuestionRepository questionRepository;

	@Autowired
	public QuestionFacade(QuestionRepository questionRepository)
	{
		this.questionRepository = questionRepository;
	}

	public Question createQuestion(String title, List<Answer> answers, Drill drill)
	{
		Question question = new Question(title, answers, drill);
		questionRepository.save(question);

		return question;
	}

	public List<Question> getAllQuestionsInDrill(UUID drillId)
	{
		//TODO ???????
		List<Question> questions = (List<Question>) questionRepository.findAll();

		return Collections.unmodifiableList(questions);
	}

	public Question updateQuestion(UUID questionId, String title)
	{
		Question question = questionRepository.getQuestionById(questionId);
		question.setTitle(title);

		questionRepository.save(question);
		return question;
	}
}
