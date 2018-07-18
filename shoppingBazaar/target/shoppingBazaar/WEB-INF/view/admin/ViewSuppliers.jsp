<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>index</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/STYLESHEET.css"/>" >
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
   <ul class="nav navbar-nav nav-pills">
 <li><a href="addsuppliers">Add Supplier</a></li>
      <li><a href="viewsuppliers">View Suppliers</a></li>
 </ul>
  </div>
</nav>
	<div>
		<table border="5" bgcolor="cyan" class="table">
			<tr>
				<td>Supplier ID</td>
				<td>Supplier Name</td>
				<td>Supplier Address</td>
				<td>Action</td>
			</tr>
				<c:forEach var="supplier" items="${supplier}">
				<tr>
					<td>${supplier.id}</td>
					<td>${supplier.name}</td>
					<td>${supplier.address}</td>
					<td><a href="supplier/delete/?id=${supplier.id}">Delete</a>|
					<a href="supplier/edit/?id=${supplier.id}">Edit</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>

</html>