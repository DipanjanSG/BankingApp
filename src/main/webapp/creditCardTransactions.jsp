<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<html>
<head>
<title> Bank of Edureka</title>
</head>
<body style='background-color:orange;'>

<h2 input style="margin-left: 30em";>Authorize Credit Card Transaction </h2> 

<form action="AuthorizeCCTransactionsServlet" method = "post">
<label for="nameOnCreditCard">Name:</label>  <input  style="margin-left: 3.7em"; type = "text" id = "nameOnCreditCard" name = "nameOnCreditCard" placeholder="Dipanjan Sengupta "> <br> <br>
<label for="cardNumber">Card Number:</label>  <input type = "text" id = "cardNumber" name = "cardNumber" placeholder="16 digit number"> <br> <br>
<label for="cvvCode">CVV2/CVC2:</label>  <input  style="margin-left: .1em"; type = "text" id = "cvvCode" name = "cvvCode" placeholder="3 digit number"> <br> <br>
<label for="amount">Amount:</label>  <input  style="margin-left: 2.6em"; type = "text" id = "amount" name = "amount" placeholder="Value less than 100000"> <br> <br>
<button type="submit" >Authorize</button>
</form>

 <c:choose>
 				<c:when test="${invalidAmount eq true}">
			
       				<h5 style="background-color:black;color: red;">Invalid Amount Entered</h5>
       				
    			</c:when>
    			
           		<c:when test="${invalidDetails eq true}">
				
       				<h5 style="background-color:black;color: red;">Invalid Credit Card Details</h5>
    			</c:when>
    			
    			<c:when test="${transactionSuccessful eq true}">
    				<h5 style="background-color:black;color: white;">Transaction Successful</h5>
				</c:when>
</c:choose>

<c:if test="${failedDBConnection eq true}">
         <h5 style="background-color:black;color: red;"> Unable to Connect to Database , check your Internet Connection or try after sometime </h5>
</c:if>


</body>
<html>
