package com.fprochazka.drill.api.user.exam;


import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

public class CreateExamRequest
{
	@NotEmpty
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
