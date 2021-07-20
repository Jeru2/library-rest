package com.mindtree.libraryspring2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.exception.LibrarySpringException;
import com.mindtree.libraryspring2.service.AuthorService;
@RestController
@RequestMapping("/author")
public class AuthorController
{
	@Autowired
	AuthorService authorServ;

	@GetMapping("/authors")
	public List<Author> getAllAuthors()
	{
		return authorServ.getAllAuthors();
	}

	@GetMapping("/id/{id}")
	public Author getAuthorById(@PathVariable int id)
	{
		return authorServ.getAuthorById(id);
	}

	@GetMapping("/book/{bookName}")
	public List<Author> getAuthorByBook(@PathVariable String bookName) throws LibrarySpringException
	{		
		return authorServ.getAuthorByBook(bookName);	
	}
}
