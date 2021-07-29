package com.mindtree.libraryspring2.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.exception.AuthorNotFoundException;
import com.mindtree.libraryspring2.exception.BookNotFoundException;
import com.mindtree.libraryspring2.exception.LibraryServiceException;
import com.mindtree.libraryspring2.repository.AuthorRepository;
import com.mindtree.libraryspring2.service.AuthorService;

/**
 * @author GodrayzZ
 * Service Layer implementation for Author
 */
@Service
public class AuthorServiceImpl implements AuthorService
{
	@Autowired
	AuthorRepository authorRepo;	
	
	/**
	 * Method to fetch all authors
	 */
	@Override
	public List<Author> getAllAuthors()
	{
		return authorRepo.findAll();
	}
	
	/**
	 * Method to fetch author with a particular id
	 */
	@Override
	public Author getAuthorById(int id) throws LibraryServiceException
	{
		System.out.println("Expect author exception~~~~~~");
		return authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author couldn't be found in records"));
	}	
	
	/**
	 * Method to fetch authors for a particular book
	 */
	@Override
	public List<Author> getAuthorByBook(String bookName) throws LibraryServiceException
	{		
		List<Author> listBook = authorRepo.findAllByBooks_name(bookName);
		if(listBook.isEmpty())
		{
			//if there are no results, an exception is thrown to inform that there are no matching entries for the book
			throw new BookNotFoundException("Book couldn't be found in records");
		}
		else
		{
			return listBook;
		}
	}
	
}
