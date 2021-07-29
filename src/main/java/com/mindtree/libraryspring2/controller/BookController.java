package com.mindtree.libraryspring2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.libraryspring2.entity.Book;
import com.mindtree.libraryspring2.exception.LibrarySpringException;
import com.mindtree.libraryspring2.service.serviceImpl.BookServiceImpl;

/**
 * @author GodrayzZ
 * REST Controller for book
 */
@RestController
@RequestMapping("/book")
public class BookController
{

	@Autowired
	BookServiceImpl bookServ;
	/**
	 * @param book - book to be added to the database
	 * @throws LibrarySpringException
	 */
	@PostMapping("/addBook")
	public void addBook(@Valid @RequestBody Book book) throws LibrarySpringException
	{
		//adding book to records
		bookServ.addBook(book);
	}

	/**
	 * @return - List of all books
	 */
	@GetMapping("/books")
	public List<Book> getAllBooks()
	{
		//returning all books
		return bookServ.getAllBooks();
	}

	/**
	 * @param id - id of book to be searched for
	 * @return - Book with requested id
	 */
	@GetMapping("/id/{id}")
	public Book getBookById(@PathVariable int id)
	{
		//returning book with specified id
		return bookServ.getBookById(id);
	}

	/**
	 * @param authorName - Name of the author for whom all written book are to be returned
	 * @return - List of book written by the requested author
	 */
	@GetMapping("/author/{authorName}")
	public List<Book> getBookByAuthor(@PathVariable String authorName)
	{
		//returning books by specified author
		return bookServ.getBookByAuthor(authorName);
	}
}
