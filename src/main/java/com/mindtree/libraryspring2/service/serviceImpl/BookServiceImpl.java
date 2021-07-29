package com.mindtree.libraryspring2.service.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.libraryspring2.entity.Author;
import com.mindtree.libraryspring2.entity.Book;
import com.mindtree.libraryspring2.exception.DuplicateInsertionException;
import com.mindtree.libraryspring2.exception.LibraryServiceException;
import com.mindtree.libraryspring2.repository.AuthorRepository;
import com.mindtree.libraryspring2.repository.BookRepository;
import com.mindtree.libraryspring2.service.BookService;
import com.mindtree.libraryspring2.service.misc.ComparableExample;


/**
 * @author GodrayzZ
 * Service Layer implementation for Book
 */
@Service
public class BookServiceImpl implements BookService
{

	/**
	 * Comparator for sorting
	 */
	public static Comparator<Book> sortByCopies = new Comparator<Book>() 
	{
		public int compare(Book book1, Book book2) 
		{
		   int copies1 = book1.getCopies();
		   int copies2 = book2.getCopies();
		   return Integer.compare(copies1, copies2);		   
	    }
	};
	@Autowired
	public BookRepository bookRepo;
	
	@Autowired
	public AuthorRepository authorRepo;
	
	//Using java functional interface Consumer which takes single input and returns nothing
	static Consumer<Author> checkBookForException(Consumer<Author> bookLambda) 
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
	
	
	
	/**
	 * @author GodrayzZ
	 * Functional interface for exception handling using lambda expressions
	 */
	@FunctionalInterface
	interface DuplicateChecker
	{
		  void check(Book book1, Book book2) throws DuplicateInsertionException;
		  
		  /*
		  default void sampleDefaultMethod()
		  {
			  System.out.println("Default method invoked!");
		  }
		  static void sampleStaticMethod()
		  {
			  System.out.println("Static method invoked");
		  }
		  class SampleClass implements DuplicateChecker
		  {
			  public void sampleClassMethod()
			  {				  
				  DuplicateChecker dpChecker;
				  dpChecker.sampleDefaultMethod();				//Can be called using the instance of interface
				  SampleClass smpClass = new SampleClass();		
				  smpClass.sampleDefaultMethod();				//Can be called using the instance of the class
				  
				  DuplicateChecker.sampleStaticMethod();		//Can only be called directly by interface name		
			  }
			  
			  @Override
			  public void sampleDefaultMethod()					//Can override to provide implementation				
			  {
				  
			  }
			  
			  @Override
			  public void sampleStaticMethod()					//Forbidden to override
			  {
				  
			  }

			@Override
			public void check(Book book1, Book book2) throws DuplicateInsertionException
			{				
			}
		  }
		  */
		  
	}
	
	/**
	 * Method to store books into database
	 */
	@Override
	public void addBook(Book book) throws LibraryServiceException
	{
		List<Book> existingBooks = bookRepo.findAll();
		
		//Exception handling in lambda expression
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
		
		//Checking if same author is present already and accordingly setting values
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
		//Exception handling in lambda expression
		book.getAuthors().forEach(checkBookForException((bk) -> bk.getBooks().put(bk.getName(), book)));
		bookRepo.save(book);
	}
	
	/**
	 * Method to fetch all books from database
	 */
	@Override
	public List<Book> getAllBooks()
	{
		List<String> names = new ArrayList<String>();
		List<Book> allBooks = bookRepo.findAll();
		allBooks.forEach(book -> names.add(book.getName()));
		
		//displaying book titles in descending order using treeset
		
		TreeSet<String> nameSet = new TreeSet<String>();
		nameSet.addAll(names);
		System.out.println("\nBook titles in descending order using treeset:");
		System.out.println(nameSet.descendingSet());
		
		TreeMap<Integer, List<String>> ratingMap = new TreeMap<Integer, List<String>>();
		for(Book currentBook : allBooks)
		{
			if(ratingMap.containsKey(currentBook.getRating()))
			{
				ratingMap.get(currentBook.getRating()).add(currentBook.getName());
			}
			else
			{
				List<String> currList = new ArrayList<String>();
				currList.add(currentBook.getName());
				ratingMap.put(currentBook.getRating(), currList);
			}
		}
		
		//displaying ratings of titles arranged using treemap
		
		System.out.println("\nRatings of titles arranged using treemap");
		System.out.println(ratingMap.descendingMap());
		
		
		List<Book> booksSortedByCopies = allBooks.stream().collect(Collectors.toList());
		Collections.sort(booksSortedByCopies, sortByCopies);
		
		//displaying books sorted by copies using comparator
		
		System.out.println("\nBooks sorted by copies using comparator:");
		System.out.println(booksSortedByCopies);
		
		List<ComparableExample> booksSortedByRating = new ArrayList<ComparableExample>();
		for(Book currBook : allBooks)
		{
			ComparableExample newExample = new ComparableExample(currBook.getRating(), currBook.getName());
			booksSortedByRating.add(newExample);
		}
		
		//displaying books sorted by rating using comparable
		
		Collections.sort(booksSortedByRating);
		System.out.println("\nBooks sorted by rating using comparable:");
		System.out.println(booksSortedByRating);
		
		//Adding copies to books using Stream map()
		
		System.out.println("\nAdding copies to books using Stream map()");
		List<Book> bookList1 = allBooks.stream().collect(Collectors.toList());
		int copiesToBeAdded = 15;			//Amount of books to be added
		bookList1.stream().map(book -> {
		book.setCopies(book.getCopies()+copiesToBeAdded);
		return book;
		}).forEach(modifiedBook -> System.out.println(modifiedBook));
		//Filtering books with category containing \'fiction\' using Stream filter()
		
		System.out.println("\nFiltering books with category containing \'fiction\' using Stream filter()");
		List<Book> bookList2 = allBooks.stream().collect(Collectors.toList());
		bookList2.stream().map(book -> {
		book.setCategory(book.getCategory().toLowerCase());
		return book;
		}).filter(modifiedBook -> modifiedBook.getCategory().contains("fiction")).forEach(filteredBook -> System.out.println(filteredBook));
		
		//Listing all titles with ratings in a concise manner using Stream reduce()
		
		System.out.println("\nListing all titles with ratings in a concise manner using Stream reduce()");
		List<Book> bookList3 = allBooks.stream().collect(Collectors.toList());
		String reduceResult = bookList3.stream().reduce("", (cumulativeResult,
				newBook) -> cumulativeResult+""+newBook.getName()+" ("+newBook.getRating()+")\n",
				(book1, book2) -> book1+""+book2);
		System.out.println(reduceResult);
		return allBooks;		
	}
	
	/**
	 * Method to fetch Book with a particular id
	 */
	@Override
	public Book getBookById(int id)
	{
		return bookRepo.findById(id).orElse(null);
	}	
	
	/**
	 * Method to fetch Books written by a particular author
	 */
	@Override
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