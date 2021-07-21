package com.mindtree.libraryspring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.exception.BookNotFoundException;
import com.mindtree.libraryspring2.exception.LibraryServiceException;
import com.mindtree.libraryspring2.repository.AuthorRepository;

@Service
public class AuthorService
{
	@Autowired
	AuthorRepository authorRepo;	
	
	public List<Author> getAllAuthors()
	{
		return authorRepo.findAll();
	}
	
	public Author getAuthorById(int id)
	{
		return authorRepo.findById(id).orElse(null);
	}	
	
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
