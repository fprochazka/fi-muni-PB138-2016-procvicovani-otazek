package com.fprochazka.drill.api.user.exam;

import java.util.List;
import java.util.UUID;

public class ExamResponse
{

	private UUID drillId;
	private UUID userId;

	private List<QuestionStatistics> statistics;

	public ExamResponse(UUID drillId, UUID userId, List<QuestionStatistics> statistics)
	{
		this.drillId = drillId;
		this.userId = userId;
		this.statistics = statistics;
	}

	public ExamResponse()
	{
	}

	public UUID getDrillId()
	{
		return drillId;
	}

	public UUID getUserId()
	{
		return userId;
	}
}
