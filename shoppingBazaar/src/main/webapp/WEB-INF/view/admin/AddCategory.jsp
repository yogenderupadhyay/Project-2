<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
   <ul class="nav navbar-nav nav-pills">
 <li><a href="addcategories">Add Category</a></li>
      <li><a href="viewcategories">View Categories</a></li>
 </ul>
  </div>
</nav>
<c:if test="${(CategorySuccessMessage!=null)}">
<i class="alert alert-success">${CategorySuccessMessage}</i><br>
</c:if>
<c:if test="${(categoryErrorMessage!=null)}">
<i class="alert alert-danger">${categoryErrorMessage}</i><br>
</c:if>
	<form action="category/save/" method="post">
    <div class="form-group">
      <label for="id">ID:</label>
      <input type="text" class="form-control" placeholder="Enter ID" name="id" value="${selectedCategory.id}">

      <label for="name">Name:</label>
      <input type="text" class="form-control" placeholder="Enter Category name" name="name" value="${selectedCategory.name}">

	<label for="description">Description:</label>
      <input type="text" class="form-control" placeholder="Enter password" name="description" value=${selectedCategory.description}>
    </div>
    <button type="submit" class="btn btn-default">create category</button>
  </form>
</body>

</html>