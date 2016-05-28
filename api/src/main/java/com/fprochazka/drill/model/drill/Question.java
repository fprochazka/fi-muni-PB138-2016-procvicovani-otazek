package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.model.Identified;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "questions")
@TypeAlias("question")
public class Question extends Identified
{

	private final String title;

	private final List<Answer> answers;

	private final Drill drill;

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
