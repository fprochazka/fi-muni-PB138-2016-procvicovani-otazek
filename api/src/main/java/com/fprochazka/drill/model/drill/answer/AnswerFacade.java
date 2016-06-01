package com.fprochazka.drill.model.drill.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerFacade
{

	private AnswerRepository answerRepository;

	@Autowired
	public AnswerFacade(AnswerRepository answerRepository)
	{
		this.answerRepository = answerRepository;
	}

	public Answer createAnswer(boolean correct, String text)
	{
		Answer answer = new Answer(correct, text);
		answerRepository.save(answer);
		return answer;
	}

	public Answer updateAnswer(UUID answerId, boolean correct, String text)
	{
		Answer answer = answerRepository.findOne(answerId);

		answer.setCorrect(correct);
		answer.setText(text);
		answerRepository.save(answer);
		return answer;
	}

	/*
	public Answer getAnswerByQuestion(Question question) {
		Answer answer = answerRepository.findOne(question.getId());
		return answer;
	}*/
}
