package com.fprochazka.drill.api.question;

import com.fprochazka.drill.api.question.answer.AnswerFactory;
import com.fprochazka.drill.model.drill.question.*;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
public class QuestionController
{

	private QuestionFacade questionFacade;
	private QuestionRepository questionRepository;
	private QuestionFactory questionFactory;
	private AnswerFactory answerFactory;

	@Autowired
	public QuestionController(
		QuestionFacade questionFacade,
		QuestionRepository questionRepository,
		QuestionFactory questionResponseFactory,
		AnswerFactory answerFactory)
	{
		this.questionFacade = questionFacade;
		this.questionRepository = questionRepository;
		this.questionFactory = questionResponseFactory;
		this.answerFactory = answerFactory;
	}

	/**
	 * Find all questions in given drill and create response objects for each
	 * found question. Return collection of created objects.
	 *
	 * @param drillId - ID of the drill we want to find questions in
	 * @return collection of response objects for found questions
	 */
	@RequestMapping(
		value = "/drill/{drillId}/question",
		method = RequestMethod.GET,
		headers = {"content-type=application/json", "accept=application/json"}
	)
	public @ResponseBody Collection<QuestionResponse> getAllQuestionsInDrill(
		@PathVariable UUID drillId
	)
	{
		Iterable<Question> questions = questionRepository.getQuestionsByDrill(drillId);
		return questionFactory.createQuestionsResponse(questions);
	}

	/**
	 * Function gets model of question (required parameters: text, answers,
	 * for each answer sign, whether it's correct). Then new ID is generated
	 * and new model is saved to DB.
	 *
	 * @param drillId         - ID of the drill we want to add question to
	 * @param questionRequest
	 */
	@RequestMapping( value = "/drill/{drillId}/question", method = RequestMethod.POST)
	public void createQuestion(
		@PathVariable UUID drillId,
		@Valid @RequestBody CreateQuestionRequest questionRequest)
	{
		List<Answer> answers = answerFactory.createAnswersFromCreateRequest(questionRequest.getAnswers());
		try {
			questionFacade.createQuestion(questionRequest.getTitle(), answers, drillId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find question with given ID and return response object of it.
	 *
	 * @param drillId    - ID of the drill we want to find question in
	 * @param questionId - ID of the question we want find
	 * @return question with given ID
	 */
	@RequestMapping(value = "/drill/{drillId}/question/{questionId}", method = RequestMethod.GET)
	public @ResponseBody QuestionResponse getQuestion(
		@PathVariable UUID drillId,
		@PathVariable UUID questionId,
		HttpServletResponse response)
	{
		Question question = questionRepository.getQuestionById(questionId);
		if (question == null) {
			response.setStatus( 404 );
		}
		return questionFactory.createQuestionResponse(question);
	}

	/**
	 * Update question
	 *
	 * @param questionId      - ID of the question we want to update
	 * @param questionRequest - updated question
	 */
	@RequestMapping(value = "/drill/{drillId}/question/{questionId}", method = RequestMethod.POST)
	public @ResponseBody QuestionResponse updateQuestion(
		@PathVariable UUID drillId,
		@PathVariable UUID questionId,
		@Valid @RequestBody UpdateQuestionRequest questionRequest,
		HttpServletResponse response
	)
	{
		List<Answer> answers = answerFactory.createAnswersFromUpdateRequest(questionRequest.getAnswers());
		Question question = null;
		try {
			question = questionFacade.updateQuestion(questionId, questionRequest.getTitle(), answers);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return questionFactory.createQuestionResponse(question);
	}
}
