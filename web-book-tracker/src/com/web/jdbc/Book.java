package com.web.jdbc;

public class Book {
	
	
	private int id;
	private String authorFirstName;
	private String authorLastName;
	private String title;
	
	
	public Book(int id, String authorFirstName, String authorLastName, String title) {
		this.id = id;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.title = title;
	}


	public Book(String authorFirstName, String authorLastName, String title) {
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.title = title;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAuthorFirstName() {
		return authorFirstName;
	}


	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}


	public String getAuthorLastName() {
		return authorLastName;
	}


	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName
				+ ", title=" + title + "]";
	}
}
