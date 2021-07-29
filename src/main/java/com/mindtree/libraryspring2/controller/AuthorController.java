package com.mindtree.libraryspring2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.exception.LibrarySpringException;
import com.mindtree.libraryspring2.service.serviceImpl.AuthorServiceImpl;
/**
 * @author GodrayzZ
 * REST Controller for author
 */
@RestController
@RequestMapping("/author")
public class AuthorController
{
	@Autowired
	AuthorServiceImpl authorServ;

	/**
	 * @return - List of all authors
	 */
	@GetMapping("/authors")
	public List<Author> getAllAuthors()
	{
		//returning all authors
		return authorServ.getAllAuthors();
	}

	/**
	 * @param id - id of author to be searched for
	 * @return - author with requested id
	 * @throws LibrarySpringException
	 */
	@GetMapping("/id/{id}")
	public Author getAuthorById(@PathVariable int id) throws LibrarySpringException
	{
		//returning author with specified id
		return authorServ.getAuthorById(id);
	}

	/**
	 * @param bookName - Name of the book for which authors are to be returned
	 * @return - List of authors who have written the requested book
	 * @throws LibrarySpringException
	 */
	@GetMapping("/book/{bookName}")
	public List<Author> getAuthorByBook(@PathVariable String bookName) throws LibrarySpringException
	{		
		//returning authors for specified Book
		return authorServ.getAuthorByBook(bookName);	
	}
}
