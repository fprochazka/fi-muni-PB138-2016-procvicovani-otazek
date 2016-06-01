package com.fprochazka.drill.model.exam.question;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.exam.StudentExam;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_exam_questions")
@TypeAlias("student_exam_question")
public class StudentExamQuestion extends Identified
{

	private Question question;
	private StudentExam exam;
	private int correct;
	private int wrong;

	public StudentExamQuestion(Question question, StudentExam exam, int correct, int wrong)
	{
		super();
		this.question = question;
		this.exam = exam;
		this.correct = correct;
		this.wrong = wrong;
	}

	public Question getQuestion()
	{
		return question;
	}

	public StudentExam getExam()
	{
		return exam;
	}

	public int getCorrect()
	{
		return correct;
	}

	public int getWrong()
	{
		return wrong;
	}

	public void setCorrect(int correct)
	{
		this.correct = correct;
	}

	public void setWrong(int wrong)
	{
		this.wrong = wrong;
	}

	@Override
	public String toString()
	{
		return "StudentExamQuestion{" +
			"id=" + getId() +
			", question=" + question +
			", exam=" + exam +
			", correct=" + correct +
			", wrong=" + wrong +
			'}';
	}

}
