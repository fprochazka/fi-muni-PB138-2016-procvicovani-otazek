package com.fprochazka.drill.model.exam.question;

import com.fprochazka.drill.model.drill.question.Question;
import com.fprochazka.drill.model.drill.question.QuestionNotFoundException;
import com.fprochazka.drill.model.drill.question.QuestionRepository;
import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.ExamNotFoundException;
import com.fprochazka.drill.model.exam.ExamRepository;
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

	public ExamQuestion createExamQuestion(UUID examId, UUID questionId, int correct, int wrong) throws QuestionNotFoundException, ExamNotFoundException, ExamQuestionNotUniqueException
	{
		Question question = questionRepository.getQuestionById(questionId);
		Exam exam = examRepository.getExamById(examId);

		if (question == null) {
			throw new QuestionNotFoundException();
		}
		if (exam == null) {
			throw new ExamNotFoundException();
		}

		ExamQuestion examQuestion = new ExamQuestion(question, exam, correct, wrong);
		if (examQuestionRepository.findOne(examQuestion.getId()) != null) {
			throw new ExamQuestionNotUniqueException();
		}

		examQuestionRepository.save(examQuestion);
		return examQuestion;

	}

	public ExamQuestion updateExamQuestionIncreaseCorrect(UUID examId, UUID questionId, int newCorrect) throws ExamNotFoundException, QuestionNotFoundException, ExamQuestionNotUniqueException
	{
		if (examRepository.findOne(examId) == null) {
			throw new ExamNotFoundException();
		}
		if (questionRepository.findOne(questionId) == null) {
			throw new QuestionNotFoundException();
		}

		ExamQuestion examQuestion = examQuestionRepository.getExamQuestionByQuestionAndExam(questionId, examId);

		if (examQuestion == null) {
			examQuestion = createExamQuestion(examId, questionId, 0, 0);
		}
		examQuestion.increaseCorrect(newCorrect);

		examQuestionRepository.save(examQuestion);
		return examQuestion;
	}

	public ExamQuestion updateExamQuestionIncreaseWrong(UUID examId, UUID questionId, int newWrong) throws ExamNotFoundException, QuestionNotFoundException, ExamQuestionNotUniqueException
	{
		if (examRepository.findOne(examId) == null) {
			throw new ExamNotFoundException();
		}
		if (questionRepository.findOne(questionId) == null) {
			throw new QuestionNotFoundException();
		}

		ExamQuestion examQuestion = examQuestionRepository.getExamQuestionByQuestionAndExam(questionId, examId);

		if (examQuestion == null) {
			examQuestion = createExamQuestion(examId, questionId, 0, 0);
		}
		examQuestion.increaseWrong(newWrong);

		examQuestionRepository.save(examQuestion);
		return examQuestion;
	}

}
