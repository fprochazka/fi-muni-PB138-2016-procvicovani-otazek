package com.fprochazka.drill.model.exam;

import com.fprochazka.drill.model.Identified;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.user.User;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exams")
@TypeAlias("exam")
public class Exam extends Identified
{

	@DBRef
	private final Drill drill;

	@DBRef
	private final User user;

	public Exam(Drill drill, User user)
	{
		super();
		this.drill = drill;
		this.user = user;
	}

	public Drill getDrill()
	{
		return drill;
	}

	public User getUser()
	{
		return user;
	}

}
