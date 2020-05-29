package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BookDbUtil {
	
	private DataSource dataSource;
	
	
	public BookDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Book> getBooks() throws Exception{
		
		List<Book> books = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
		//get a connection
		myConn = dataSource.getConnection();
		
		// create sql statement
		String sql = "select * from book order by title";
		
		myStmt = myConn.createStatement();
		
		// execute query
		myRs = myStmt.executeQuery(sql);
		
		// process result set
		
		while(myRs.next()) {
			
			// retrieve data from result set row
		
			int id = myRs.getInt("id");
			String authorFirstName = myRs.getString("author_first_name");
			String authorLastName = myRs.getString("author_last_name");
			String title = myRs.getString("title");
			
			Book tempBook = new Book(id,authorFirstName,authorLastName,title);
			
			books.add(tempBook);
			
		}
		return books;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addBook(Book theBook) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		
		try {
			// get DB connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into book (title, author_first_name, author_last_name) values (?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
					
			// set the param values for the book
			myStmt.setString(1,theBook.getTitle());
			myStmt.setString(2,theBook.getAuthorFirstName());
			myStmt.setString(3,theBook.getAuthorLastName());
			
			// execute sql insert
			myStmt.execute();
			
		} finally {
			// clean up JDBC objects
			close(myConn,myStmt,null);
		}
	}

	public List<Book> searchBookByTitle(String givenTitle) throws Exception {
		
		List<Book> books = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
		//get a connection
		myConn = dataSource.getConnection();
		
		
		myStmt = myConn.prepareStatement("select * from book where title like ?");
		myStmt.setString(1, "%" + givenTitle + "%");
		
		
		
		// execute query
		myRs= myStmt.executeQuery();
		
		// process result set
		
		while(myRs.next()) {
			
			// retrieve data from result set row
		
			int id = myRs.getInt("id");
			String authorFirstName = myRs.getString("author_first_name");
			String authorLastName = myRs.getString("author_last_name");
			String title = myRs.getString("title");
			
			Book tempBook = new Book(id,authorFirstName,authorLastName,title);
			
			books.add(tempBook);
			
		}
		return books;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		
	}
}
