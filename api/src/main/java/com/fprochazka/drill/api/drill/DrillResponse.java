package com.fprochazka.drill.api.drill;

import java.util.UUID;

public class DrillResponse
{

	private UUID id;
	private String code;
	private String name;

	public DrillResponse(UUID id, String code, String name)
	{
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public DrillResponse()
	{
	}

	public UUID getId()
	{
		return id;
	}
	public String getCode()
	{
		return code;
	}
	public String getName()
	{
		return name;
	}
}
