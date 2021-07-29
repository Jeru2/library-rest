package com.mindtree.libraryspring2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.libraryspring2.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>
{
	//finds all authors which have written the book <bookName>
	List<Author> findAllByBooks_name(String bookName);
	
	@Query(value = "SELECT * FROM library_authors", nativeQuery = true)
	List<Author> findAll();

}
