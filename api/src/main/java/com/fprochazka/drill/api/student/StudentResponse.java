package com.fprochazka.drill.api.student;

import java.util.UUID;

/**
 * Created by viki on 11.06.16.
 */
public class StudentResponse
{
	private UUID id;
	private int uco;
	private String email;

	public StudentResponse(UUID id, int uco, String email)
	{
		this.id = id;
		this.uco = uco;
		this.email = email;
	}

	public StudentResponse()
	{
	}

	public UUID getId()
	{
		return id;
	}

	public int getUco()
	{
		return uco;
	}

	public String getEmail()
	{
		return email;
	}
}
