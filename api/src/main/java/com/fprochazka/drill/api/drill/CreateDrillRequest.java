package com.fprochazka.drill.api.drill;

public class CreateDrillRequest
{

	private String name;

	public CreateDrillRequest(String name)
	{
		this.name = name;
	}

	public CreateDrillRequest()
	{
	}

	public String getName()
	{
		return name;
	}

}
