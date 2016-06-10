package com.fprochazka.drill.api.drill;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateDrillRequest
{
	@NotEmpty
	private String code;

	@NotEmpty
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
