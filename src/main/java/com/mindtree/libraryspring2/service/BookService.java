package com.mindtree.libraryspring2.service;

import java.util.List;

import com.mindtree.libraryspring2.entity.Book;
import com.mindtree.libraryspring2.exception.LibraryServiceException;

public interface BookService
{

	void addBook(Book book) throws LibraryServiceException;

	List<Book> getAllBooks();

	Book getBookById(int id);

	List<Book> getBookByAuthor(String author);

}