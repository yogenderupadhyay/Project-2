<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
   <ul class="nav navbar-nav nav-pills">
 <li><a href="addproducts">Add Products</a></li>
 <li><a href="viewproducts">View Products</a></li>
 </ul>
  </div>
</nav>
	<div>
		<table class="table">
			<tr>
				<td>Product ID</td>
				<td>Product Name</td>
				<td>Product Description</td>
				<td>Action</td>
			</tr>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.description}</td>
					<td><a href="product/delete/?id=${product.id}">Delete</a>|
					<a href="product/edit/?id=${product.id}">Edit</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>