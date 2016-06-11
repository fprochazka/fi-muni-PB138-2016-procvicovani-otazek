package com.fprochazka.drill.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController
{

	private ErrorAttributes errorAttributes;

	private final static String ERROR_PATH = "/error";

	@Autowired
	public ErrorController(ErrorAttributes errorAttributes)
	{
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping(value = ERROR_PATH)
	public @ResponseBody ResponseEntity<Map<String, Object>> error(HttpServletRequest request)
	{
		Map<String, Object> body = getErrorAttributes(request, false);
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(body, status);
	}

	@Override
	public String getErrorPath()
	{
		return ERROR_PATH;
	}

	private Map<String, Object> getErrorAttributes(
		HttpServletRequest request,
		boolean includeStackTrace
	)
	{
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

	private HttpStatus getStatus(HttpServletRequest request)
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
