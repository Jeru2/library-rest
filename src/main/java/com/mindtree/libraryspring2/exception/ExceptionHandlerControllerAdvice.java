package com.mindtree.libraryspring2.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
	protected ResponseEntity<Map<String, Object>> handleConflict(LibrarySpringException ex, HttpServletRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.BAD_REQUEST);	//The response code is sent here
		response.put("code", "400");
		response.put("requested URI", request.getRequestURI());
		System.out.println("Handled an exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);	//The response code is sent here
		//200 201 204 400 404 500 
	}	
	
	/**
	 * @param ex - Exception instance for Author not found exception
	 * @param request - WebRequest instance
	 * @return - ResponseEntity which contains response for user and status code
	 */
	@ExceptionHandler(value = {AuthorNotFoundException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(AuthorNotFoundException ex, HttpServletRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.NOT_FOUND);	//The response code is sent here
		response.put("code", "404");
		response.put("requested URI", request.getRequestURI());
		System.out.println("Author not found exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
	}
	/**
	 * @param ex - Exception instance for Book not found exception
	 * @param request - WebRequest instance
	 * @return - ResponseEntity which contains response for user and status code
	 */
	@ExceptionHandler(value = {BookNotFoundException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(BookNotFoundException ex, HttpServletRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.NOT_FOUND);	//The response code is sent here
		response.put("code", "404");
		response.put("requested URI", request.getRequestURI());
		System.out.println("Book not found exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
	}	
	/**
	 * @param ex - Exception instance for duplicate insertion exception
	 * @param request - WebRequest instance
	 * @return - ResponseEntity which contains response for user and status code
	 */
	@ExceptionHandler(value = {DuplicateInsertionException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(DuplicateInsertionException ex, HttpServletRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.CONFLICT);	//The response code is sent here
		response.put("code", "409");
		response.put("requested URI", request.getRequestURI());
		System.out.println("Duplicate entry attempted exception");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);	
	}
	
	@ExceptionHandler(value = {NoContentException.class})
	protected ResponseEntity<Map<String, Object>> handleConflict(NoContentException ex, HttpServletRequest request) 
	{
		response = new HashMap<String, Object>();
		response.put("message", ex.getMessage());
		response.put("HTTP Status", HttpStatus.NO_CONTENT);	//The response code is sent here
		response.put("code", "204");
		response.put("requested URI", request.getRequestURI());
		System.out.println("No content found");
		ex.printStackTrace();
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);	
	}	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", "405");
        response.put("status", "Method not supported");
        return new ResponseEntity<Object>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", "415");
        response.put("status", "Media type not supported");
        return new ResponseEntity<Object>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(org.springframework.beans.TypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", "400");
        response.put("status", "Type mismatch");
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request)
	{
		Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("code", "500");
        response.put("status", "Internal server error");
        return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}
