package com.fprochazka.drill.model.drill.question;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillRepository;
import com.fprochazka.drill.model.exceptions.NotFoundException;
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

	public Question createQuestion(String title, List<Answer> answers, UUID drillId) throws NotFoundException
	{
		Drill drill = drillRepository.findOne(drillId);
		if (drill == null) {
			throw new NotFoundException();
		}
		Question question = new Question(title, answers, drill);

		questionRepository.save(question);

		return question;
	}

	public Question updateQuestion(UUID questionId, String title, List<Answer> answers) throws NotFoundException
	{
		Question question = questionRepository.getQuestionById(questionId);
		if (question == null) {
			throw new NotFoundException();
		}

		question.setTitle(title);
		question.setAnswers(answers);

		questionRepository.save(question);
		return question;
	}


}
