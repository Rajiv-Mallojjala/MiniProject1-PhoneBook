<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	function confirmDelete(){
		return confirm("Are you sure you want to delete?")
	}
	
</script>
</head>
<body>
<h2>All Contacts</h2>
<a href="loadForm"> + Add new Contact </a>
	<table border="1">
		<thead>
			<tr>
				<th>Contact Name</th>
				<th>Contact Email</th>
				<th>Contact Number</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contacts}" var="c">
				<tr>
					<td>${c.contactName}</td>
					<td>${c.contactEmail}</td>
					<td>${c.contactPhone}</td>
					<td><a href="edit?cid=${c.contactId}">Edit</a> &nbsp; &nbsp; 
						<a href="delete?cid=${c.contactId}" onclick="return confirmDelete()">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="loadForm">Back</a>
</body>
</html>