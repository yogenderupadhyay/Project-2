<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="${root}/checkout"> Checkout</a>  <br>
<div class="row">
<c:forEach var="cart" items="${cartList}">
 <div class="col-xs-3">
 <table class="table table-condensed table-hover" style="border-width:0;">
<tr><td><img alt="" src=" ${ImagesFolder}${cart.productID}.PNG"><br>
${cart.productName}<br>
<i class="fa fa-inr"> ${cart.price}</i><br>
qty: ${cart.quantity}<br>
<a href="${root}/remove/${cart.id}">REMOVE CART</a></td></tr></table>
</div>

</c:forEach>
</div>
</body>
</html>