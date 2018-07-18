<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	isELIgnored="false" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>index</title>
<link rel="stylesheet" type="text/css"
	href="/resources/css/STYLESHEET.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<jsp:include page="product_menu.jsp"></jsp:include>
	<c:if test="${(registrationSuccessMessage!=null)}">
		<i class="alert alert-success">${registrationSuccessMessage}</i>
		<br>
	</c:if>
	<c:if test="${(registrationErrorMessage!=null)or (AuthorizationErrorMessage!=null)}">
		<i class="alert alert-danger">${registrationErrorMessage}</i>
		<i class="alert alert-danger">${AuthorizationErrorMessage}</i>
		<br>
	</c:if>
	<c:if
		test="${(cartUnableToLoadError==null) and (isUserClickedLogin!=true)and 
		(isUserClickedMyCart!=true)and(isUserSelectedProduct!=true)and(isUserClickedRegister!=true) and (isUserClickedHome==true)}">
		<jsp:include page="Home.jsp"></jsp:include>
	</c:if>
	<c:if
		test="${(cartUnableToLoadError!=null) or (isUserSelectedProduct==true)}">
		<jsp:include page="selected_product.jsp"></jsp:include>
	</c:if>
	<c:if
		test="${((errorMessage!=null) or(isUserClickedLogin==true))and(loggedInUserID==null)}">
		<jsp:include page="login.jsp"></jsp:include>
	</c:if>
	<c:if test="${isUserClickedMyCart==true}">
		<jsp:include page="cart.jsp"></jsp:include>
	</c:if>
	<c:if test="${(isAdmin==true)and (isUserSelectedProduct!=true) and (isUserClickedMyCart!=true)and(isUserClickedHome!=true)}">
		<jsp:include page="admin/adminhome.jsp"></jsp:include>

	</c:if>
	<div>
		<c:if test="${isUserClickedRegister==true}">
			<jsp:include page="registration.jsp"></jsp:include>
		</c:if>

	</div>

</body>
</html>