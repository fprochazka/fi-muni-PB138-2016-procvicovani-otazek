package com.fprochazka.drill.model.exam.question;

import com.fprochazka.drill.model.drill.answer.Answer;
import com.fprochazka.drill.model.drill.answer.AnswerRepository;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentExamQuestionFacade
{

	private StudentExamQuestionRepository studentExamQuestionRepository;
	private AnswerRepository answerRepository;

	@Autowired
	public StudentExamQuestionFacade(StudentExamQuestionRepository studentExamQuestionRepository, AnswerRepository answerRepository)
	{
		this.studentExamQuestionRepository = studentExamQuestionRepository;
		this.answerRepository = answerRepository;
	}


	public StudentExamQuestion updateStudentExamQuestion(Question question)
	{
		StudentExamQuestion studentExamQuestion = studentExamQuestionRepository.findOne(question.getId());

		//TODO
		Answer answer = answerRepository.getAnswerByQuestion(question);

		if (answer.isCorrect()) {
			studentExamQuestion.setCorrect(studentExamQuestion.getCorrect() + 1);
		} else {
			studentExamQuestion.setWrong(studentExamQuestion.getWrong() + 1);
		}
		studentExamQuestionRepository.save(studentExamQuestion);
		return studentExamQuestion;
	}

}
