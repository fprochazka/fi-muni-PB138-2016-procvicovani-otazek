package com.fprochazka.drill.api.exam;

import java.util.List;
import java.util.UUID;

/**
 * Created by viki on 09.06.16.
 */
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
