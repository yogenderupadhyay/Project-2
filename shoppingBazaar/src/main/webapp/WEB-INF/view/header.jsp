<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="images" value="/Resources/images" />
    <c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Header</title>
<style type="text/css">
.navbar-default .navbar-brand:hover,
.navbar-default .navbar-brand:focus {
color: white;
}
.nav-custom
{color:white;}
</style>
</head>
<body class="body">
<nav class="navbar navbar-inverse nav-custom" class="active">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href=""><img src="<c:url value='/Resources/logo.png'/>"/> <!-- alt="logo" height="50px" width="50px"> --></a>
      <a class="navbar-brand" href="${root}/">SHOPPING BAZAR</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
     <form class="navbar-form navbar-left" action="${root}/search">
      <div class="form-group">
        <input type="text" class="form-control" name="searchString" placeholder="Search" required>
      </div>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>

      <ul class="nav navbar-nav navbar-right" style="color:white;">
      <li><a href="${root}//home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
      <c:if test="${(loggedInUserID==null)}">
        <li><a href="${root}/signUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="${root}/login"><span class="glyphicon glyphicon-log-in"></span> login</a></li>
        </c:if>
        <c:if test="${(loggedInUserID!=null)}">
        <li><a href="${root}/userHome"><span class="glyphicon glyphicon-user active"></span>${welcomeMessage}</a></li>
       <li><a href="${root}/logout"><span class="glyphicon glyphicon-log-out"></span> logout</a></li>
        </c:if>
        <li><a href="${root}/mycart"><span class="glyphicon glyphicon-shopping-cart"></span>cart <span class="badge">${size}</span></a></li>
      </ul>
    </div>
  </div>
</nav> 
	
</body>
</html>