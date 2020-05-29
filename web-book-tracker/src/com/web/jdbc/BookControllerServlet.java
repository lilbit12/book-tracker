package com.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class BookControllerServlet
 */
@WebServlet("/BookControllerServlet")
public class BookControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDbUtil bookDbUtil;
	
	@Resource(name="jdbc/web_book_tracker")
	private DataSource dataSource;
	
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our book db util ... and pass in the conn pool /datasource
		
		try {
			bookDbUtil = new BookDbUtil(dataSource);
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			if(theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listBooks(request,response);
				break;
			
			case "ADD":
				addBook(request,response);
				break;
				
			case "Search":
				searchBook(request,response);
				break;
				
			default:
				listBooks(request,response);
			}
			
			//listBooks(request,response);
			
		} catch(Exception exc) {
			throw new ServletException(exc);
		}
	}


	private void searchBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		  
		  	String title = request.getParameter("title");
		  
		  	// get book from db util List<Book> books =
		  	List<Book> tmp = bookDbUtil.searchBookByTitle(title);
		  
		  
		    // add books to the request
			request.setAttribute("BOOK_LIST", tmp);
			
			// send to JSP page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-books.jsp");
			dispatcher.forward(request, response);
		 
	}


	private void addBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read book info from form data
		String title = request.getParameter("title");
		String authorFirstName = request.getParameter("authorFirstName");
		String authorLastName = request.getParameter("authorLastName");
		
		// create a new book object
		Book theBook = new Book(authorFirstName,authorLastName,title);
		
		
		// add the student to the database
		bookDbUtil.addBook(theBook);
		
		// send back to main page
		listBooks(request,response);
	}


	private void listBooks(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get book from db util
		List<Book> books = bookDbUtil.getBooks();
		
		// add books to the request
		request.setAttribute("BOOK_LIST", books);
		
		// send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-books.jsp");
		dispatcher.forward(request, response);
	}
	
}
