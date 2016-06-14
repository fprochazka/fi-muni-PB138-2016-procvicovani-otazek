package com.fprochazka.drill.model.api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api")
public class ApiProperties
{

	private Client client;

	public Client getClient()
	{
		return client;
	}

	public void setClient(Client client)
	{
		this.client = client;
	}

	public static class Client
	{
		private String origin = null;

		public String getOrigin()
		{
			return origin;
		}

		public void setOrigin(String origin)
		{
			this.origin = origin;
		}
	}

}
