package com.fprochazka.drill.model.drill.question;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.drill.answer.Answer;
import com.fprochazka.drill.model.drill.Drill;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "questions")
@TypeAlias("question")
public class Question extends Identified
{

	private String title;

	private List<Answer> answers;

	private Drill drill;

	public Question(String title, List<Answer> answers, Drill drill)
	{
		super();
		this.title = title;
		this.answers = answers;
		this.drill = drill;
	}

	public String getTitle()
	{
		return title;
	}

	public List<Answer> getAnswers()
	{
		return Collections.unmodifiableList(answers);
	}

	public Drill getDrill()
	{
		return drill;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public String toString()
	{
		return "Question{" +
			"id=" + getId() +
			", title='" + title + '\'' +
			", answers=" + answers +
			", drill=" + drill +
			'}';
	}

}
