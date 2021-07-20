package com.mindtree.libraryspring2.exception;

import java.io.Serializable;

public class LibrarySpringException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;

	public LibrarySpringException()
	{
		super();
	}

	public LibrarySpringException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public LibrarySpringException(String message)
	{
		super(message);
	}

	public LibrarySpringException(Throwable cause)
	{
		super(cause);
	}
	
}
