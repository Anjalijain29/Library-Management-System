package com.nrifintech.lms.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(value=Exception.class)
	public ModelAndView exceptionHandler() {
		System.out.println("plz show the page");
		ModelAndView modelAndView = new ModelAndView("exceptionError");
		return modelAndView;
	}

}

