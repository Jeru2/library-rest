package com.mindtree.libraryspring2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.libraryspring2.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>
{
	//List<Author> findAllByBooks_name(String bookName);

	//@Query(value = "SELECT * FROM library_authors WHERE library_authors.id IN (SELECT author_id FROM book_author WHERE book_id IN (SELECT library_books.id FROM library_books WHERE name = ?1))", nativeQuery = true)
	@Query("from Author A inner join A.books B where B.name = ?1")
	List<Author> findAllByBooksname(String bookName);

}
