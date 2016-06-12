package com.fprochazka.drill.model.drill.question;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.drill.Drill;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "questions")
@TypeAlias("question")
public class Question extends Identified
{

	private String text;

	private List<Answer> answers;

	@DBRef
	private Drill drill;

	public Question(String text, List<Answer> answers, Drill drill)
	{
		super();
		this.text = text;
		this.answers = answers;
		this.drill = drill;
	}

	public String getText()
	{
		return text;
	}

	public List<Answer> getAnswers()
	{
		return Collections.unmodifiableList(answers);
	}

	public Drill getDrill()
	{
		return drill;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString()
	{
		return "Question{id=" + getId() + ", text='" + text + "'}";
	}

}
