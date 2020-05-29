<!DOCTYPE html>
<html>

<head>

<title>Add Book</title>

<link type="text/css" rel = "stylesheet" href="css/style.css">
<link type="text/css" rel = "stylesheet" href="css/add-book-style.css">


</head>

<body>

	<div id="wrapper">
		<div id="header">
		<h2>Book Web Tracker</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Add book</h3>
		
		<form action ="BookControllerServlet" method="GET">
			<input type = "hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Title:</label>
						<td><input type="text" name="title" /></td>
					</tr>
					
					<tr>
						<td><label>Author First Name:</label>
						<td><input type="text" name="authorFirstName" /></td>
					</tr>
					
					<tr>
						<td><label>Author Last Name:</label>
						<td><input type="text" name="authorLastName" /></td>
					</tr>
					
					
					<tr>
					<td><label></label></td>
					<td><input type = "submit" value="Save" class="save" /></td>
					</tr>
					
					
				</tbody>
			</table>
	</div>

</body>

</html>