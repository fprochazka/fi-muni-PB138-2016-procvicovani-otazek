package com.fprochazka.drill.fixtures;

import com.fprochazka.drill.model.exam.Exam;
import com.fprochazka.drill.model.exam.ExamRepository;
import com.fprochazka.drill.model.exam.question.ExamQuestion;
import com.fprochazka.drill.model.exam.question.ExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamTestFixtures
{

	public static Exam examWithDrillPB138;
	public static ExamQuestion examQuestion1;
	public static ExamQuestion examQuestion2;

	@Autowired
	private ExamQuestionRepository examQuestionRepository;

	@Autowired
	private ExamRepository examRepository;

	public void install()
	{
		examWithDrillPB138 = new Exam(DrillTestFixtures.drillPB138, UserTestFixtures.user123456);
		examRepository.save(examWithDrillPB138);
		examQuestion1 = new ExamQuestion(DrillTestFixtures.drillPB138question1, examWithDrillPB138, 2, 3);
		examQuestionRepository.save(examQuestion1);
		examQuestion2 = new ExamQuestion(DrillTestFixtures.drillPB138question2, examWithDrillPB138, 1, 0);
		examQuestionRepository.save(examQuestion2);
	}

}

