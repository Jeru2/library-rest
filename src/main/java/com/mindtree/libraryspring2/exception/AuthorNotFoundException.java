package com.mindtree.libraryspring2.exception;

public class AuthorNotFoundException extends LibraryServiceException
{
	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException()
	{
	}

	public AuthorNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public AuthorNotFoundException(String message)
	{
		super(message);
	}

	public AuthorNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
