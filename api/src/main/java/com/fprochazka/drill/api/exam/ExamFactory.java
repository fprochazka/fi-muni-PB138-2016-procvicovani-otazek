package com.fprochazka.drill.api.exam;

import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.question.ExamQuestion;
import com.fprochazka.drill.model.exam.question.ExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by viki on 09.06.16.
 */

@Service
public class ExamFactory
{

	private ExamQuestionRepository examQuestionRepository;

	@Autowired
	public ExamFactory(ExamQuestionRepository examQuestionRepository)
	{
		this.examQuestionRepository = examQuestionRepository;
	}

	public ExamResponse createExamResponse(Exam exam, List<QuestionStatistics> statistics)
	{
		return new ExamResponse(exam.getDrill().getId(), exam.getUser().getId(), statistics);
	}

	public ExamResponse createExamResponse(Exam exam)
	{
		List<ExamQuestion> questions = examQuestionRepository.getExamQuestionsByExam(exam.getId());
		return createExamResponse(exam, createQuestionsStatistics(questions));
	}

	public List<ExamResponse> createExamsResponse(Iterable<Exam> exams)
	{
		return StreamSupport
			.stream(exams.spliterator(), false)
			.map(this::createExamResponse)
			.collect(Collectors.toList());
	}

	public QuestionStatistics createQuestionStatistics(ExamQuestion question)
	{
		return new QuestionStatistics(question.getQuestion().getId(), question.getCorrect(), question.getWrong());
	}

	public List<QuestionStatistics> createQuestionsStatistics(Iterable<ExamQuestion> questions)
	{
		return StreamSupport
			.stream(questions.spliterator(), false)
			.map(this::createQuestionStatistics)
			.collect(Collectors.toList());
	}
}
