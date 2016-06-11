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

	@Autowired
	private ExamQuestionRepository examQuestionRepository;

	@Autowired
	private ExamRepository examRepository;

	public void install()
	{
		Exam exam = new Exam(DrillTestFixtures.drillPB138, StudentTestFixtures.student123456);
		examRepository.save(exam);
		ExamQuestion examQuestion1 = new ExamQuestion(DrillTestFixtures.drillPB138question1, exam, 2, 3);
		examQuestionRepository.save(examQuestion1);
		ExamQuestion examQuestion2 = new ExamQuestion(DrillTestFixtures.drillPB138question2, exam, 1, 0);
		examQuestionRepository.save(examQuestion2);
	}

}
