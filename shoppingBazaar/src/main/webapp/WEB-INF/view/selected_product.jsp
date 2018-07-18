<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${searchError!=null}">
		<i class="alert alert-danger">${searchError}</i>
</c:if>
<c:if test="${getProduct==true }">
<div class="row">
<div class="col-xs-3">
<table class="table">
<tr>
<td>
${selectedProducts.id}
<img alt="" src="/Resources/images/uploads/${selectedProducts.id}.PNG"></td>
</tr>
<tr><td>${selectedProducts.name}<br>
<i class="fa fa-inr"> ${selectedProducts.price}</i><br>
<detail>
<summary>show more</summary>
<p> ${selectedProducts.description}</p>
</detail>
<a href="${root}/cartadd/${selectedProducts.id}">Add to Cart</a></td>
</tr>
</table></div></div>
</c:if>
<c:if test="${searchProducts==true}">
<div class="row">
<% int i=0; %>
<c:forEach var="selectedProduct" items="${selectedProducts}">
 <div class="col-xs-3">
 <table class="table table-condensed table-hover">
<tr><td><% i=i+1; %></td><td><img alt="" src="Resources/images/uploads/${selectedProduct.id}.PNG">
${selectedProduct.name} <br>

<i class="fa fa-inr">${selectedProduct.price}</i><br>
${selectedProduct.description} <br>
<a href="${root}/cartadd/${selectedProduct.id}">Add to Cart</a></td></tr></table></div>
</c:forEach></div>
</c:if>
</body>
</html>