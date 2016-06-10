package com.fprochazka.drill.api.drill;

public class CreateDrillRequest
{

	private String code;
	private String name;

	public CreateDrillRequest(String code, String name)
	{
		this.code = code;
		this.name = name;
	}

	public CreateDrillRequest()
	{
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
