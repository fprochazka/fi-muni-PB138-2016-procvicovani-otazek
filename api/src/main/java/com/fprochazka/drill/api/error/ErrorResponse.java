package com.fprochazka.drill.api.error;

import java.util.List;

public class ErrorResponse
{

	private List<ErrorMessageResponse> errors;

	public ErrorResponse(List<ErrorMessageResponse> errors)
	{
		this.errors = errors;
	}

	public List<ErrorMessageResponse> getErrors()
	{
		return errors;
	}

	public static class ErrorMessageResponse
	{
		private String code;
		private String message;

		ErrorMessageResponse(String code, String message)
		{
			this.code = code;
			this.message = message;
		}

		public String getCode()
		{
			return code;
		}

		public String getMessage()
		{
			return message;
		}
	}
}
