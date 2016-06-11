package com.fprochazka.drill.model.authentication.password;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("password-encoder")
public class PasswordEncoderProperties
{

	private Bcrypt bcrypt;

	public Bcrypt getBcrypt()
	{
		return bcrypt;
	}

	public void setBcrypt(Bcrypt bcrypt)
	{
		this.bcrypt = bcrypt;
	}

	public static class Bcrypt
	{
		private int strength = 11;

		public int getStrength()
		{
			return strength;
		}

		public void setStrength(int strength)
		{
			this.strength = strength;
		}
	}

}
