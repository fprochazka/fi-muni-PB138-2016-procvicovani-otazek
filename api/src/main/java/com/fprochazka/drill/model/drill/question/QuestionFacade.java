package com.fprochazka.drill.model.drill.question;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillNotFoundException;
import com.fprochazka.drill.model.drill.DrillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Question createQuestion(UUID drillId, String text, List<Answer> answers) throws DrillNotFoundException
	{
		Drill drill = drillRepository.findOne(drillId);
		if (drill == null) {
			throw new DrillNotFoundException();
		}
		Question question = new Question(text, answers, drill);

		questionRepository.save(question);

		return question;
	}

	public Question updateQuestion(UUID drillId, UUID questionId, String text, List<Answer> answers) throws DrillNotFoundException
	{
		Question question = questionRepository.getQuestionByIdAndDrill(questionId, drillId);
		if (question == null) {
			throw new DrillNotFoundException();
		}

		question.setText(text);
		question.setAnswers(answers);

		questionRepository.save(question);
		return question;
	}

}
