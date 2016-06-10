package com.fprochazka.drill.model.exam.question;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_exam_questions")
@TypeAlias("student_exam_question")
public class ExamQuestion extends Identified
{

	@DBRef
	private Question question;

	@DBRef
	private Exam exam;

	private int correct;
	private int wrong;

	public ExamQuestion(Question question, Exam exam, int correct, int wrong)
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

	public Exam getExam()
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

	public void increaseCorrect(int newCorrect)
	{
		this.correct += newCorrect;
	}

	public void increaseWrong(int newWrong)
	{
		this.wrong += newWrong;
	}

	@Override
	public String toString()
	{
		return "ExamQuestion{" +
			"id=" + getId() +
			", question=" + question +
			", exam=" + exam +
			", correct=" + correct +
			", wrong=" + wrong +
			'}';
	}

}
