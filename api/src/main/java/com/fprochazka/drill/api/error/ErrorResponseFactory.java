package com.fprochazka.drill.api.error;

import com.fprochazka.drill.model.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ErrorResponseFactory
{

	public ErrorResponse createErrorResponse(ApiException exception)
	{
		return new ErrorResponse(Collections.singletonList(createErrorMessageResponse(exception)));
	}

	public ErrorResponse.ErrorMessageResponse createErrorMessageResponse(ApiException exception)
	{
		return new ErrorResponse.ErrorMessageResponse(exception.getTextCode(), exception.getTextMessage());
	}

}
