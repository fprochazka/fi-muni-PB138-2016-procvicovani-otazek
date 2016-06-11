package com.fprochazka.drill.api.user;

import java.util.UUID;

public class UserResponse
{

	private UUID id;
	private int uco;
	private String email;

	public UserResponse(UUID id, int uco, String email)
	{
		this.id = id;
		this.uco = uco;
		this.email = email;
	}

	public UserResponse()
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
