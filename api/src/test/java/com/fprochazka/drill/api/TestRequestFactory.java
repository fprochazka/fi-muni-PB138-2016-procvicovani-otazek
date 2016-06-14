package com.fprochazka.drill.api;

import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.question.Answer;
import com.fprochazka.drill.model.drill.question.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestRequestFactory
{

	public Map< String, Object > createCreateDrillRequest(Drill drill)
	{
		Map< String, Object> reqDrill = new HashMap<>();
		reqDrill.put( "code", drill.getCode() );
		reqDrill.put( "name", drill.getName() );

		return reqDrill;
	}

	public Map< String, Object > createCreateQuestionRequest(Question question)
	{
		Map< String, Object> reqQuestion = new HashMap<>();
		reqQuestion.put( "text", question.getText() );
		reqQuestion.put( "drill", question.getDrill() );

		List< Map< String, Object> > answers = new ArrayList<>();
		for (Answer answer: question.getAnswers())
		{
			Map< String, Object> reqAnswer = new HashMap<>();
			reqAnswer.put( "text", answer.getText() );
			reqAnswer.put( "correct", answer.isCorrect() );
			answers.add( reqAnswer );
		}
		reqQuestion.put( "answers", answers );

		return reqQuestion;
	}

}
