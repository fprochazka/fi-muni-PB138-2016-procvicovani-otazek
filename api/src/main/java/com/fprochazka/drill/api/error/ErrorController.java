package com.fprochazka.drill.api.error;

import com.fprochazka.drill.model.api.*;
import com.fprochazka.drill.model.api.authentication.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.rmi.UnexpectedException;
import java.util.Collections;

@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController
{

	public final static String CODE_REQUEST_VALIDATION_ERROR = "request-validation-error";
	public final static String CODE_INTERNAL_SERVER_ERROR = "internal-server-error";

	private ErrorAttributes errorAttributes;

	private ErrorResponseFactory errorResponseFactory;

	private final static String ERROR_PATH = "/error";

	@Autowired
	public ErrorController(ErrorAttributes errorAttributes, ErrorResponseFactory errorResponseFactory)
	{
		this.errorAttributes = errorAttributes;
		this.errorResponseFactory = errorResponseFactory;
	}

	@RequestMapping(value = ERROR_PATH)
	public @ResponseBody ErrorResponse error(HttpServletRequest request, HttpServletResponse response, Exception exception)
	{
		if (exception instanceof BadRequestException) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return errorResponseFactory.createErrorResponse((ApiException) exception);

		} else if (exception instanceof UnprocessableEntityException) {
			response.setStatus(422);
			return errorResponseFactory.createErrorResponse((ApiException) exception);

		} else if (exception instanceof AuthenticationException) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return errorResponseFactory.createErrorResponse((ApiException) exception);

		} else if (exception instanceof ResourceNotFoundException) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return errorResponseFactory.createErrorResponse((ApiException) exception);
		}

		HttpStatus status = getServletStatus(request);
		ErrorResponse.ErrorMessageResponse errorMessage;
		switch(status) {
			case INTERNAL_SERVER_ERROR:
				errorMessage = new ErrorResponse.ErrorMessageResponse(CODE_INTERNAL_SERVER_ERROR, status.getReasonPhrase());
				break;
			default:
				errorMessage = new ErrorResponse.ErrorMessageResponse(null, status.getReasonPhrase());
		}

		response.setStatus(status.value());
		return new ErrorResponse(Collections.singletonList(errorMessage));
	}

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}

	private HttpStatus getServletStatus(HttpServletRequest request)
	{
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
