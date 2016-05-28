package com.fprochazka.drill.api.drill;

import java.util.UUID;

public class DrillResponse
{

	private UUID id;

	private String name;

	public DrillResponse(UUID id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public DrillResponse()
	{
	}

	public UUID getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
}
