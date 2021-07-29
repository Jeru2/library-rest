package com.mindtree.libraryspring2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 * @author GodrayzZ
 * Global exception handler which handles exceptions occurring in the application
 */
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler
{
	Map<String, Object> response;
	
	@ExceptionHandler(value = {LibrarySpringException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(LibrarySpringException ex, WebRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.BAD_REQUEST);	//The response code is sent here
		response.put("code", "400");
		System.out.println("Handled an exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	//The response code is sent here
	}	
	
	/**
	 * @param ex - Exception instance for Author not found exception
	 * @param request - WebRequest instance
	 * @return - ResponseEntity which contains response for user and status code
	 */
	@ExceptionHandler(value = {AuthorNotFoundException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(AuthorNotFoundException ex, WebRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.NOT_FOUND);	//The response code is sent here
		response.put("code", "404");
		System.out.println("Author not found exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}
	/**
	 * @param ex - Exception instance for Book not found exception
	 * @param request - WebRequest instance
	 * @return - ResponseEntity which contains response for user and status code
	 */
	@ExceptionHandler(value = {BookNotFoundException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(BookNotFoundException ex, WebRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.NOT_FOUND);	//The response code is sent here
		response.put("code", "404");
		System.out.println("Book not found exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}	
	/**
	 * @param ex - Exception instance for duplicate insertion exception
	 * @param request - WebRequest instance
	 * @return - ResponseEntity which contains response for user and status code
	 */
	@ExceptionHandler(value = {DuplicateInsertionException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(DuplicateInsertionException ex, WebRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.CONFLICT);	//The response code is sent here
		response.put("code", "409");
		System.out.println("Duplicate entry attempted exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);	
	}	
	
}
