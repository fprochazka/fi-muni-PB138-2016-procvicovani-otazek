package com.fprochazka.drill.api.error;

import com.fprochazka.drill.model.api.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ErrorResponseFactory
{

	public ErrorResponse createErrorResponse(String textCode, MethodArgumentNotValidException argumentNotValidException)
	{
		List<ErrorResponse.ErrorMessageResponse> messages = new ArrayList<>();

		BindingResult bindingResult = argumentNotValidException.getBindingResult();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			messages.add(new ErrorResponse.ErrorMessageResponse(
				textCode,
				"Field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage() + ": " + Arrays.toString(fieldError.getCodes())
			));
		}

		return new ErrorResponse(messages);
	}

	public ErrorResponse createErrorResponse(String textCode, String message)
	{
		return new ErrorResponse(Collections.singletonList(
			new ErrorResponse.ErrorMessageResponse(textCode, message)
		));
	}

	public ErrorResponse createErrorResponse(ApiException exception)
	{
		return new ErrorResponse(Collections.singletonList(createErrorMessageResponse(exception)));
	}

	public ErrorResponse.ErrorMessageResponse createErrorMessageResponse(ApiException exception)
	{
		return new ErrorResponse.ErrorMessageResponse(exception.getTextCode(), exception.getTextMessage());
	}

}
