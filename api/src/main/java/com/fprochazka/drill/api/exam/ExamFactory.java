package com.fprochazka.drill.api.exam;

import com.fprochazka.drill.model.exam.Exam;
import org.springframework.stereotype.Service;

/**
 * Created by viki on 09.06.16.
 */

@Service
public class ExamFactory {

	public ExamResponse createExamResponse( Exam exam ) {
		return new ExamResponse( exam.getDrill().getId(), exam.getStudent().getId() );
	}
}
