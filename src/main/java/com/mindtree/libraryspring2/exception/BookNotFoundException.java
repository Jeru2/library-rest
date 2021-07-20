package com.mindtree.libraryspring2.exception;

import java.io.Serializable;

public class BookNotFoundException extends LibraryServiceException implements Serializable
{
	private static final long serialVersionUID = 1L;

	public BookNotFoundException()
	{
	}

	public BookNotFoundException(String message)
	{
		super(message);
	}

	public BookNotFoundException(Throwable cause)
	{
		super(cause);
	}

	public BookNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	
}
