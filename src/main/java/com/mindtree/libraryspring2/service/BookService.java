package com.mindtree.libraryspring2.service;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.entity.Book;
import com.mindtree.libraryspring2.exception.DuplicateInsertionException;
import com.mindtree.libraryspring2.exception.LibraryServiceException;
import com.mindtree.libraryspring2.repository.AuthorRepository;
import com.mindtree.libraryspring2.repository.BookRepository;

@Service
public class BookService
{
	@Autowired
	public BookRepository bookRepo;
	
	@Autowired
	public AuthorRepository authorRepo;
	
	static Consumer<Author> BookLambdaWrapper(Consumer<Author> bookLambda) 
	{
	    return input -> 
	    {
	        try 
	        {
	            bookLambda.accept(input);
	        } 
	        catch (NullPointerException e) 
	        {
	            System.out.println("An exception occured in lamda expression: " + e.getMessage());
	        }
	    };
	}
	
	@FunctionalInterface
	interface DuplicateChecker
	{
		   void check(Book book1, Book book2) throws DuplicateInsertionException;
	}
	
	public void addBook(Book book) throws LibraryServiceException
	{
		List<Book> existingBooks = bookRepo.findAll();
		
		DuplicateChecker dupChecker = (book1, book2) ->	{
			if(book1.equals(book2))
			{
				throw new DuplicateInsertionException("Attempting to insert duplicate records");
			}
		};
		for(Book currentExistingBook : existingBooks)
		{
			dupChecker.check(currentExistingBook, book);
		}		
		
		/*
		List<Book> existingBooks = bookRepo.findAll();
		for(Book currentExistingBook : existingBooks)
		{
			if(currentExistingBook.equals(book))
			{
				throw new DuplicateInsertionException("Attempting to insert duplicate records");
			}
		}
		*/
		
		List<Author> currentBookAuthors = book.getAuthors();
		List<Author> databaseAuthors = authorRepo.findAll();
		
		for(Iterator<Author> currentBookAuthorIterator = currentBookAuthors.iterator(); currentBookAuthorIterator.hasNext();)
		{            
			Author currAuthor = currentBookAuthorIterator.next(); 
				
			for(Iterator<Author> databaseAuthorIterator = databaseAuthors.iterator(); databaseAuthorIterator.hasNext();)
			{
				Author dbAuthor = databaseAuthorIterator.next();
				if(currAuthor.getBirthYear().equals(dbAuthor.getBirthYear()) && currAuthor.getName().equals(dbAuthor.getName()))
				{
					currentBookAuthors.remove(currAuthor);
					currentBookAuthors.add(dbAuthor);
				}
			}
		}
		book.setAuthors(currentBookAuthors);
		book.getAuthors().forEach(BookLambdaWrapper((bk) -> bk.getBooks().put(bk.getName(), book)));
		bookRepo.save(book);
	}
	
	public List<Book> getAllBooks()
	{
		return bookRepo.findAll();		
	}
	
	public Book getBookById(int id)
	{
		return bookRepo.findById(id).orElse(null);
	}	
	
	public List<Book> getBookByAuthor(String author)
	{
		return bookRepo.findAllByAuthors_name(author);
	}
}


/*
AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
appContext.scan("com.mindtree.libraryspringdatajpa");
appContext.refresh(); 
bookServ = (BookService) appContext.getBean("bookService");
bookServ.bookOperations(); 
appContext.close();
*/