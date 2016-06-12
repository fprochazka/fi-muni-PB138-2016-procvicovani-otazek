package com.fprochazka.drill.api.drill.question;

import com.fprochazka.drill.api.drill.question.answer.AnswerFactory;
import com.fprochazka.drill.api.drill.question.answer.AnswerResponse;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class QuestionFactory
{

	private AnswerFactory answerFactory;

	@Autowired
	public QuestionFactory(AnswerFactory answerFactory)
	{
		this.answerFactory = answerFactory;
	}

	/**
	 * Create response object of given question. Create response objects
	 * for each answer for question.
	 *
	 * @param question
	 * @return response object of the question
	 */
	public QuestionResponse createQuestionResponse(Question question)
	{
		Collection<AnswerResponse> answers = answerFactory.createAnswersResponse(question.getAnswers());
		return new QuestionResponse(question.getId(), question.getText(), answers);
	}

	/**
	 * Create collection of response objects of given questions.
	 *
	 * @param questions
	 * @return collection of response objects of given questions
	 */
	public List<QuestionResponse> createQuestionsResponse(Iterable<Question> questions)
	{
		return StreamSupport
			.stream(questions.spliterator(), false)
			.map(this::createQuestionResponse)
			.collect(Collectors.toList());
	}

	/*
	public Question createQuestionFromCreateRequest( CreateQuestionRequest question ) {
		Collection<Answer> answers = answerFactory.createAnswersFromCreateRequest( question.getAnswers() );
		return new Question( question.getText(), answers );
	}

	public List<Question> createQuestionsFromCreateRequest( Iterable<CreateQuestionRequest> questions ) {
		return StreamSupport
			.stream(questions.spliterator(), false)
			.map(this::createQuestionFromCreateRequest)
			.collect(Collectors.toList());
	}

	public Question createQuestionFromUpdateRequest( UpdateQuestionRequest question ) {
		Collection<Answer> answers = answerFactory.createAnswersFromUpdateRequest( question.getAnswers() );
		return new Question( question.getText(), answers );
	}

	public List<Question> createQuestionsFromUpdateRequest( Iterable<UpdateQuestionRequest> questions ) {
		return StreamSupport
			.stream(questions.spliterator(), false)
			.map(this::createQuestionFromUpdateRequest)
			.collect(Collectors.toList());
	}
	*/
}
