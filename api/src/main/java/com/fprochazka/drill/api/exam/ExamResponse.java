package com.fprochazka.drill.api.exam;

import com.fprochazka.drill.model.drill.question.Question;

import java.util.List;
import java.util.UUID;

/**
 * Created by viki on 09.06.16.
 */
public class ExamResponse
{

	private UUID drillId;
	private UUID studentId;

	private List<QuestionStatistics> statistics;

	public ExamResponse(UUID drillId, UUID studentId, List<QuestionStatistics> statistics)
	{
		this.drillId = drillId;
		this.studentId = studentId;
		this.statistics = statistics;
	}

	public ExamResponse()
	{
	}

	public UUID getDrillId()
	{
		return drillId;
	}

	public UUID getStudentId()
	{
		return studentId;
	}
}
