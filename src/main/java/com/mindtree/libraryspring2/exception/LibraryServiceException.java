package com.mindtree.libraryspring2.exception;

import java.io.Serializable;

public class LibraryServiceException extends LibrarySpringException implements Serializable
{
	private static final long serialVersionUID = 1L;

	public LibraryServiceException()
	{
		super();
	}

	public LibraryServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public LibraryServiceException(String message)
	{
		super(message);
	}

	public LibraryServiceException(Throwable cause)
	{
		super(cause);
	}
}
