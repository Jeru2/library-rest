package com.mindtree.libraryspring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.repository.AuthorRepository;

@Service
public class AuthorService
{
	@Autowired
	AuthorRepository authorRepo;	
	
	public List<Author> getAllAuthors()
	{
		authorRepo.findAll().forEach(author -> System.out.println(author));
		return authorRepo.findAll();
	}
	
	public Author getAuthorById(int id)
	{
		return authorRepo.findById(id).orElse(null);
	}	
	
	public List<Author> getAuthorByBook(String bookName)
	{		
		return authorRepo.findAllByBooks_name(bookName);
	}
	
}
