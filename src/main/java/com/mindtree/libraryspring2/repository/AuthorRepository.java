package com.mindtree.libraryspring2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.libraryspring2.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>
{

	List<Author> findAllByBooks_name(String bookName);

}
