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
${supplierSuccessMessage} ${supplierErrorMessage}
	<form action="supplier/save/" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name='id' value="${selectedSupplier.id}"></td>
			</tr>
			<tr>
			<td>Name</td>
			<td><input type="text" name='name' value="${selectedSupplier.name}"></td>
			</tr>
			<tr>
			<td>Address</td>
			<td><input type="text" name='address' value="${selectedSupplier.address}"></td>
			<tr>
				<td><input type="submit" value='Submit'></td>
			</tr>
		</table>
	</form>
</body>

</html>