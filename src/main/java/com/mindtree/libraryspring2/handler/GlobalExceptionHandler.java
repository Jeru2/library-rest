package com.mindtree.libraryspring2.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mindtree.libraryspring2.exception.LibrarySpringException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	Map<String, Object> response;
	
	@ExceptionHandler(value = {LibrarySpringException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(LibrarySpringException ex, WebRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("status", HttpStatus.BAD_REQUEST);
		response.put("body", null);
		response.put("error", true);
		System.out.println("Handled an exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}	
	
}
