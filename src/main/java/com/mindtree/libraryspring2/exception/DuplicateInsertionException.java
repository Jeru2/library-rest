package com.mindtree.libraryspring2.exception;

import java.io.Serializable;

public class DuplicateInsertionException extends LibraryServiceException implements Serializable
{
	private static final long serialVersionUID = 1L;

	public DuplicateInsertionException()
	{
	}

	public DuplicateInsertionException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DuplicateInsertionException(String message)
	{
		super(message);
	}

	public DuplicateInsertionException(Throwable cause)
	{
		super(cause);
	}
}
