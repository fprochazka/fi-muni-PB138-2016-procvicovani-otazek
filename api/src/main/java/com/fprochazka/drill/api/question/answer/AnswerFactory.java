package com.fprochazka.drill.api.question.answer;

import com.fprochazka.drill.model.drill.question.Answer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by viki on 09.06.16.
 */

@Service
public class AnswerFactory
{

	/**
	 * Create response object of given answer.
	 *
	 * @param answer
	 * @return response object of the answer
	 */
	public AnswerResponse createAnswerResponse(Answer answer)
	{
		return new AnswerResponse(answer.getText(), answer.isCorrect());
	}

	/**
	 * Create collection of response objects for each given answer.
	 *
	 * @param answers
	 * @return coolection of response objects for answers
	 */
	public List<AnswerResponse> createAnswersResponse(Iterable<Answer> answers)
	{
		return StreamSupport
			.stream(answers.spliterator(), false)
			.map(this::createAnswerResponse)
			.collect(Collectors.toList());
	}


	public Answer createAnswerFromCreateRequest(CreateAnswerRequest answer)
	{
		return new Answer(answer.isCorrect(), answer.getText());
	}

	public List<Answer> createAnswersFromCreateRequest(Iterable<CreateAnswerRequest> answers)
	{
		return StreamSupport
			.stream(answers.spliterator(), false)
			.map(this::createAnswerFromCreateRequest)
			.collect(Collectors.toList());
	}

	public Answer createAnswerFromUpdateRequest(UpdateAnswerRequest answer)
	{
		return new Answer(answer.isCorrect(), answer.getText());
	}

	public List<Answer> createAnswersFromUpdateRequest(Iterable<UpdateAnswerRequest> answers)
	{
		return StreamSupport
			.stream(answers.spliterator(), false)
			.map(this::createAnswerFromUpdateRequest)
			.collect(Collectors.toList());
	}

}
