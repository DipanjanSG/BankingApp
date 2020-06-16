<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title> Bank of Edureka</title>
</head>
<body style='background-color:orange;'>

<h2> Bank of Edureka </h2>
<h3>Authorize Credit Card Transaction </h3>
<form action="AuthorizeCCTransactionsServlet" method = "post">
<label for="nameOnCreditCard">Name:</label>  <input type = "text" id = "nameOnCreditCard" name = "nameOnCreditCard"> <br> <br>
<label for="cardNumber">Card Number:</label>  <input type = "text" id = "cardNumber" name = "cardNumber"> <br> <br>
<label for="cvvCode">CVV2/CVC2:</label>  <input type = "text" id = "cvvCode" name = "cvvCode"> <br> <br>
<label for="amount">Amount:</label>  <input type = "text" id = "amount" name = "amount"> <br> <br>
<button type="submit" >Authorize</button>
</form>

 <c:choose>
 				<c:when test="${invalidAmount eq true}">
			
       				<h5 style="color:red;">Invalid Amount Entered</h5>
       				
    			</c:when>
    			
           		<c:when test="${invalidDetails eq true}">
				
       				<h5 style="color:red;">Invalid Credit Card Details</h5>
    			</c:when>
    			
    			<c:when test="${transactionSuccessful eq true}">
				
    				<h5>Transaction Successful</h5>
				</c:when>
</c:choose>




</body>
<html>
