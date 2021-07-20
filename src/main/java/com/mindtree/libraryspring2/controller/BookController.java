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
import com.mindtree.libraryspring2.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController
{

	@Autowired
	BookService bookServ;
	
	@PostMapping("/addBook")
	public void addBook(@Valid @RequestBody Book book) throws LibrarySpringException
	{
		bookServ.addBook(book);
	}

	@GetMapping("/books")
	public List<Book> getAllBooks()
	{
		return bookServ.getAllBooks();
	}

	@GetMapping("/id/{id}")
	public Book getBookById(@PathVariable int id)
	{
		return bookServ.getBookById(id);
	}

	@GetMapping("/author/{authorName}")
	public List<Book> getBookByAuthor(@PathVariable String authorName)
	{
		return bookServ.getBookByAuthor(authorName);
	}
}
