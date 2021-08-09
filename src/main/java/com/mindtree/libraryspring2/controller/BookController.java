package com.mindtree.libraryspring2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Map<String, Object>> addBook(@Valid @RequestBody Book book) throws LibrarySpringException
	{
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.CREATED);
		response.put("code", "201");
		
		//adding book to records
		bookServ.addBook(book);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * @return - List of all books
	 */
	@GetMapping("/books")
	public ResponseEntity<Map<String, Object>> getAllBooks()
	{
		//returning all books
		List<Book> result = bookServ.getAllBooks();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.OK);
		response.put("code", "200");
		response.put("Response data", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * @param id - id of book to be searched for
	 * @return - Book with requested id
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<Map<String, Object>> getBookById(@PathVariable int id)
	{
		//returning book with specified id
		Book result = bookServ.getBookById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.OK);
		response.put("code", "200");
		response.put("Response data", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * @param authorName - Name of the author for whom all written books are to be returned
	 * @return - List of book written by the requested author
	 */
	@GetMapping("/author/{authorName}")
	public ResponseEntity<Map<String, Object>> getBookByAuthor(@PathVariable String authorName)
	{
		//returning books by specified author
		List<Book> result = bookServ.getBookByAuthor(authorName);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.OK);
		response.put("code", "200");
		response.put("Response data", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
