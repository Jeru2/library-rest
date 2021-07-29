package com.mindtree.libraryspring2.service;

import java.util.List;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.exception.AuthorNotFoundException;
import com.mindtree.libraryspring2.exception.LibraryServiceException;

public interface AuthorService
{

	List<Author> getAllAuthors();

	Author getAuthorById(int id) throws AuthorNotFoundException, LibraryServiceException;

	List<Author> getAuthorByBook(String bookName) throws LibraryServiceException;

}