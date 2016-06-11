package com.fprochazka.drill.api.error;

import com.fprochazka.drill.model.api.*;
import com.fprochazka.drill.model.api.authentication.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.NestedServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController
{

	public final static String CODE_REQUEST_VALIDATION_ERROR = "request-validation-error";
	public final static String CODE_INTERNAL_SERVER_ERROR = "internal-server-error";

	private ErrorResponseFactory errorResponseFactory;

	private final static String ERROR_PATH = "/error";

	@Autowired
	public ErrorController(ErrorResponseFactory errorResponseFactory)
	{
		this.errorResponseFactory = errorResponseFactory;
	}

	@RequestMapping(value = ERROR_PATH)
	public @ResponseBody ErrorResponse error(HttpServletRequest request, HttpServletResponse response)
	{
		Exception dispatcherServletException = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
		if (dispatcherServletException instanceof MethodArgumentNotValidException) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return errorResponseFactory.createErrorResponse(CODE_REQUEST_VALIDATION_ERROR, (MethodArgumentNotValidException) dispatcherServletException);
		}

		Exception requestDispatcherException = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		Exception exception = (requestDispatcherException instanceof NestedServletException) ? (Exception) requestDispatcherException.getCause() : null;

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
		response.setStatus(status.value());

		switch(status) {
			case INTERNAL_SERVER_ERROR:
				return errorResponseFactory.createErrorResponse(CODE_INTERNAL_SERVER_ERROR, status.getReasonPhrase());
			default:
				return errorResponseFactory.createErrorResponse(null, status.getReasonPhrase());
		}
	}

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}

	private HttpStatus getServletStatus(HttpServletRequest request)
	{
		Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (statusCode != null) {
			try {
				return HttpStatus.valueOf(statusCode);
			} catch (Exception ex) {
			}
		}
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
