package com.mindtree.libraryspring2.service.misc;

public class ComparableExample implements Comparable<ComparableExample>
{
	private int rating;
	private String name;
	
	public ComparableExample()
	{
		super();
	}
	
	public ComparableExample(int rating, String name)
	{
		super();
		this.rating = rating;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	//overriding the compareTo method defined in the Comparable interface to provide our implementation
	//which decides how the sorting will be conducted when Collections.sort will be called
	
	@Override
	public int compareTo(ComparableExample book)
	{
		if(rating == book.getRating())
		{
			return 0;
		}
		else if(rating > book.getRating())
		{
			return 1;
		}
		else
		{
			return -1;
		}		
	}

	@Override
	public String toString()
	{
		return "ComparableExample [rating=" + rating + ", name=" + name + "]";
	}
}
