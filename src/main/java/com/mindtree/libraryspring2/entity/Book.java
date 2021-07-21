package com.mindtree.libraryspring2.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "library_books")
public class Book
{
	@Id
	@SequenceGenerator(name = "seq1", initialValue = 100, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
	
	private int id;
	@Column
	@NotBlank
	private String name;
	@Column
	@Min(value = 1)
	@Max(value = 10)
	private int rating;
	@Column
	@NotBlank(message = "Cannot be blank")
	private String language;
	@Column
	@NotBlank(message = "Cannot be blank")
	private String category;
	@Column
	private int copies;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "book_author", 
			joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
			)
	private List<Author> authors;
	public Book()
	{
		super();
	}
	
	public Book(String name, int rating, String language, String category, int copies)
	{
		super();
		this.name = name;
		this.rating = rating;
		this.language = language;
		this.category = category;
		this.copies = copies;
	}

	public Book(String name, int rating, String language, String category, List<Author> authors, int copies)
	{
		super();
		this.name = name;
		this.rating = rating;
		this.language = language;
		this.category = category;
		this.authors = authors;
		this.copies = copies;
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

	public int getRating()
	{
		return rating;
	}

	public void setRating(int rating)
	{
		this.rating = rating;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public int getCopies()
	{
		return copies;
	}

	public void setCopies(int copies)
	{
		this.copies = copies;
	}

	public List<Author> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<Author> authors)
	{
		this.authors = authors;
	}

	@Override
	public String toString()
	{
		return "Book [id=" + id + ", name=" + name + ", rating=" + rating + ", language=" + language + ", category="
				+ category + ", copies=" + copies + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rating;
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
		Book other = (Book) obj;
		
		if (category == null)
		{
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (language == null)
		{
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}
}