package com.mindtree.libraryspring2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Map<String, Object>> getAllAuthors()
	{
		//returning all authors
		List<Author> result = authorServ.getAllAuthors();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.OK);
		response.put("code", "200");
		response.put("Response data", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * @param id - id of author to be searched for
	 * @return - author with requested id
	 * @throws LibrarySpringException
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<Map<String, Object>> getAuthorById(@PathVariable int id) throws LibrarySpringException
	{
		//returning author with specified id
		Author result = authorServ.getAuthorById(id);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.OK);
		response.put("code", "200");
		response.put("Response data", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * @param bookName - Name of the book for which authors are to be returned
	 * @return - List of authors who have written the requested book
	 * @throws LibrarySpringException
	 */
	@GetMapping("/book/{bookName}")
	public ResponseEntity<Map<String, Object>> getAuthorByBook(@PathVariable String bookName) throws LibrarySpringException
	{		
		//returning authors for specified Book
		List<Author> result = authorServ.getAuthorByBook(bookName);	
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.OK);
		response.put("code", "200");
		response.put("Response data", result);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
