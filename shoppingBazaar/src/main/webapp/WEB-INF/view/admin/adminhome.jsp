<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>index</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/STYLESHEET.css"/>" >
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
		<div class="container-fluid">
		<div class="row">
    <div class="col-xs-3 ">
    <nav class="navbar navbar-inverse">
   <ul class="nav">
<a href="addcategories" class="center"></a>
 <li><a href="${root}/viewcategories" >Manage Category</a></li>
    <li><a href="${root}/viewsuppliers"> Manage Suppliers</a></li>
    <li><a href="${root}/viewproducts"> Manage Products</a></li>
 </ul>
</nav>
    </div> 
   
    <div class="col-xs-9 success" style="background-color:lavenderblush;">
    <c:if test="${isAdminClickedAddCategories==true }">
		<jsp:include page="AddCategory.jsp"></jsp:include>
	</c:if>
	<c:if test="${isAdminClickeViewCategories==true }">
		<jsp:include page="ViewCategory.jsp"></jsp:include>
	</c:if>
	 <c:if test="${isAdminClickedAddProducts==true }">
		<jsp:include page="AddProduct.jsp"></jsp:include>
	</c:if>
	<c:if test="${isAdminClickedViewProducts==true }">
		<jsp:include page="ViewProducts.jsp"></jsp:include>
	</c:if>
	<c:if test="${isAdminClickedAddSuppliers==true }">
		<jsp:include page="AddSupplier.jsp"></jsp:include>
	</c:if>
	<c:if test="${isAdminClickedViewSuppliers==true }">
		<jsp:include page="ViewSuppliers.jsp"></jsp:include>
	</c:if></div>
  </div>
  </div>
</body>
</html>