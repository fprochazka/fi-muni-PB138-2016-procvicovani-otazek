package com.fprochazka.drill.api.exam;

import com.sun.istack.internal.NotNull;

import java.util.UUID;

/**
 * Created by viki on 10.06.16.
 */
public class CreateExamRequest
{
	@NotNull
	private UUID drillId;

	public CreateExamRequest(UUID drillId)
	{
		this.drillId = drillId;
	}

	public CreateExamRequest()
	{
	}

	public UUID getDrillId()
	{
		return drillId;
	}
}
