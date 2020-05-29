<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<Head>
	<title> Book Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel = "stylesheet" href="css/search-book-style.css">
</Head>


<body>

	<div id="wrapper">
		<div id="header">
			<h2>Book Tracker</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
		<!-- put textbox -->
			
		<form action ="BookControllerServlet" method="GET">
		Search by title: <td><input type = "text" name="title"/></td>
		<td><input type = "submit" name="command" value="Search" /></td>
		</form>
		
		<!-- put a new button: Add Book -->
		<input type="button" value="Add book" 
		onclick="window.location.href='add-book-form.jsp'; return false;"
		class="add-book-button"
		/>
		

			<table>
				<tr>
				 	<th>Title</th>
				 	<th>Author First Name</th>
				 	<th>Author Last Name</th>
				</tr>
				
				<c:forEach var="tempBook" items="${BOOK_LIST}">
				
					<tr>
						<td> ${tempBook.title} </td>
						<td> ${tempBook.authorFirstName} </td>
						<td> ${tempBook.authorLastName} </td>
					</tr>
					
				</c:forEach>
				
			</table>	

	</div>
	
	
</body>

</html>