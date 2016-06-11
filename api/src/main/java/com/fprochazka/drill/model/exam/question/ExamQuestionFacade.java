package com.fprochazka.drill.model.exam.question;

import com.fprochazka.drill.model.drill.question.QuestionRepository;
import com.fprochazka.drill.model.exam.ExamRepository;
import com.fprochazka.drill.model.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExamQuestionFacade
{

	private ExamQuestionRepository examQuestionRepository;
	private ExamRepository examRepository;
	private QuestionRepository questionRepository;

	@Autowired
	public ExamQuestionFacade(ExamQuestionRepository examQuestionRepository, ExamRepository examRepository, QuestionRepository questionRepository)
	{
		this.examQuestionRepository = examQuestionRepository;
		this.examRepository = examRepository;
		this.questionRepository = questionRepository;
	}

	public ExamQuestion updateExamQuestionIncreaseCorrect(UUID examId, UUID questionId, int newCorrect) throws NotFoundException
	{
		if (examRepository.findOne(examId) == null) {
			throw new NotFoundException();
		}
		if (questionRepository.findOne(questionId) == null) {
			throw new NotFoundException();
		}

		ExamQuestion examQuestion = examQuestionRepository.getExamQuestionByQuestionAndExam(questionId, examId);

		if (examQuestion == null) {
			throw new NotFoundException();
		}
		examQuestion.increaseCorrect(newCorrect);
		examQuestionRepository.save(examQuestion);
		return examQuestion;
	}

	public ExamQuestion updateExamQuestionIncreaseWrong(UUID examId, UUID questionId, int newWrong) throws NotFoundException
	{
		if (examRepository.findOne(examId) == null) {
			throw new NotFoundException();
		}
		if (questionRepository.findOne(questionId) == null) {
			throw new NotFoundException();
		}

		ExamQuestion examQuestion = examQuestionRepository.getExamQuestionByQuestionAndExam(questionId, examId);

		if (examQuestion == null) {
			throw new NotFoundException();
		}
		examQuestion.increaseWrong(newWrong);
		examQuestionRepository.save(examQuestion);
		return examQuestion;
	}

}
