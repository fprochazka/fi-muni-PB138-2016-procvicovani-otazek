package com.fprochazka.drill.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * @author Filip Prochazka <433457@mail.muni.cz>
 */
public class Identified
{

	@Id
	private final UUID id;

	public Identified()
	{
		this.id = UUID.randomUUID();
	}

	public UUID getId()
	{
		return id;
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Identified)) return false;

		Identified that = (Identified) o;

		return id.equals(that.id);

	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
}
