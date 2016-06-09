package com.fprochazka.drill.model.exam.question;

import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamQuestionFacade
{

	private ExamQuestionRepository examQuestionRepository;

	@Autowired
	public ExamQuestionFacade(ExamQuestionRepository examQuestionRepository)
	{
		this.examQuestionRepository = examQuestionRepository;
	}


	public ExamQuestion updateExamQuestion(Question question)
	{
		ExamQuestion ExamQuestion = examQuestionRepository.findOne(question.getId());

		//TODO

		examQuestionRepository.save(ExamQuestion);
		return ExamQuestion;
	}
	/*
	public ExamQuestion getExamQuestionByExamAndQuestion(UUID questionId, UUID examId) {
		//TODO



		return
	}

	public ExamQuestion getExamQuestionByQuestion(UUID questionId) {
		ExamQuestion examQuestion = examQuestionRepository.getExamQuestionByQuestion(questionId);
		return examQuestion;
	}



	/*
	public ExamQuestion updateExamQuestion
	 */

	/*
	public Exam updateExamIncreateCorrect(UUID examId, UUID questionId, int newCorrect) {
		//
		Exam exam =

		examRepository.save(exam);
		return exam;
	}

	public Exam updateExamIncreateWrong(UUID examId, UUID questionId, int newWrong) {

		Exam exam =

			examRepository.save(exam);
		return exam;
	}*/



}
