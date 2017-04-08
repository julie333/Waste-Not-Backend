package com.project.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		logger.error(String.format("Data integrity violation for [%s] request to URL [%s].", request.getMethod(),
			request.getRequestURL()), ex);
	}

}
