package com.mindtree.libraryspring2.exception;

public class NoContentException extends LibraryServiceException
{
	private static final long serialVersionUID = 1L;

	public NoContentException()
	{
	}

	public NoContentException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NoContentException(String message)
	{
		super(message);
	}

	public NoContentException(Throwable cause)
	{
		super(cause);
	}
}
