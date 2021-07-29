package com.mindtree.libraryspring2.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.libraryspring2.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
	
	List<Book> findAllByAuthors_name(String author);
	
	@Query("FROM Book")
	List<Book> findAll();

}