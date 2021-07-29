package com.mindtree.libraryspring2.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "library_authors")
public class Author
{
	@Id
	@SequenceGenerator(name = "seq", initialValue = 500, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	private int id;
	@Column
	@NotBlank
	private String name;
	@Column
	@NotBlank
	private String birthYear;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "authors")
	@MapKey(name = "name")
	private Map<String, Book> books;

	public Author()
	{
		super();
		books = new HashMap<String, Book>();		
	}

	public Author(String name, String birthYear)
	{
		super();
		this.name = name;
		this.birthYear = birthYear;
	}
	
	public Author(String name, String birthYear, Map<String, Book> books)
	{
		super();
		this.name = name;
		this.birthYear = birthYear;
		this.books = books;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBirthYear()
	{
		return birthYear;
	}

	public void setBirthYear(String birthYear)
	{
		this.birthYear = birthYear;
	}

	public Map<String, Book> getBooks()
	{
		return books;
	}

	public void setBooks(Map<String, Book> books)
	{
		this.books = books;
	}

	@Override
	public String toString()
	{
		return "Author [id=" + id + ", name=" + name + ", birthYear=" + birthYear + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthYear == null) ? 0 : birthYear.hashCode());
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (birthYear == null)
		{
			if (other.birthYear != null)
				return false;
		} else if (!birthYear.equals(other.birthYear))
			return false;
		if (books == null)
		{
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
