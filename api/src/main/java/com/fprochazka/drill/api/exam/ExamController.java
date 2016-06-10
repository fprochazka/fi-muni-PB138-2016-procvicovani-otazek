package com.fprochazka.drill.api.exam;

import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.ExamFacade;
import com.fprochazka.drill.model.exam.ExamRepository;
import com.fprochazka.drill.model.exam.question.ExamQuestion;
import com.fprochazka.drill.model.exam.question.ExamQuestionFacade;
import com.fprochazka.drill.model.exam.question.ExamQuestionRepository;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import com.fprochazka.drill.model.exceptions.NotUniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
public class ExamController
{

	private ExamFactory examFactory;
	private ExamFacade examFacade;
	private ExamQuestionFacade examQuestionFacade;
	private ExamQuestionRepository examQuestionRepository;
	private ExamRepository examRepository;

	@Autowired
	public ExamController(
		ExamFactory examFactory,
		ExamFacade examFacade,
		ExamQuestionFacade examQuestionFacade,
		ExamQuestionRepository examQuestionRepository,
		ExamRepository examRepository)
	{
		this.examFactory = examFactory;
		this.examFacade = examFacade;
		this.examQuestionFacade = examQuestionFacade;
		this.examQuestionRepository = examQuestionRepository;
		this.examRepository = examRepository;
	}

	/**
	 * Function creates new exam for given user and drill. If exam already exists, does nothing.
	 *
	 * @param request - ID of drill we want to create exam for
	 */
	@RequestMapping(value = "/user/{userId}/exam", method = RequestMethod.POST)
	public void createExam(
		@PathVariable("userId") UUID userId,
		@RequestBody CreateExamRequest request,
		HttpServletResponse response)
	{
		try {
			examFacade.createExam(request.getDrillId(), userId);
		} catch (NotUniqueException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find all exams in database of given user.
	 *
	 * @return response objects for all found exams
	 */
	@RequestMapping(value = "/user/{userId}/exam", method = RequestMethod.GET)
	public @ResponseBody Collection<ExamResponse> getAllExams(@PathVariable UUID userId)
	{
		List<Exam> exams = examRepository.getExamsByStudent( userId );
		return null;
	}

	/**
	 * Find exam with given ID in database, return it's response object.
	 *
	 * @return response object for found exam
	 */
	@RequestMapping(value = "/user/{userId}/exam/{examId}", method = RequestMethod.GET)
	public @ResponseBody ExamResponse getExam(
		@PathVariable UUID examId)
	{
		Exam exam = examRepository.getExamById(examId);
		List<ExamQuestion> answers = examQuestionRepository.getExamQuestionsByExam( examId );

		return examFactory.createExamResponse(exam, examFactory.createQuestionsStatistics(answers));
	}

	/**
	 * Update questions of given exam with given answers.
	 *
	 * @param answers - list of user's answers to questions of exam
	 */
	@RequestMapping(value = "/user/{userId}/exam/{examId}", method = RequestMethod.PUT)
	public void updateExam(
		@PathVariable UUID userId,
		@PathVariable UUID examId,
		@Valid @RequestBody Collection<UpdateExamRequest> answers)
	{

		Map<UUID, Integer> correct = new HashMap<>();
		Map<UUID, Integer> wrong = new HashMap<>();

		for (UpdateExamRequest request : answers) {
			if (request.getCorrect()) {
				int count = correct.containsKey(request.getQuestionId()) ?
					correct.get(request.getQuestionId()) : 0;
				correct.put(request.getQuestionId(), count + 1);
			} else {
				int count = wrong.containsKey(request.getQuestionId()) ?
					wrong.get(request.getQuestionId()) : 0;
				wrong.put(request.getQuestionId(), count + 1);
			}
		}

		for (Map.Entry<UUID, Integer> request : correct.entrySet()) {
			try {
				examQuestionFacade.updateExamQuestionIncreaseCorrect( examId, request.getKey(), request.getValue() );
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}

		for (Map.Entry<UUID, Integer> request : wrong.entrySet()) {
			try {
				examQuestionFacade.updateExamQuestionIncreaseWrong( examId, request.getKey(), request.getValue() );
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
